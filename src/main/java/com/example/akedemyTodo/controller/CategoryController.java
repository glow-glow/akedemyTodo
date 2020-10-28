package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.search.CategorySearchValues;
import com.example.akedemyTodo.service.CategoryService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ComponentScan(basePackages = {"com.example.*"})
@RequestMapping("/category")
public class CategoryController {


    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public List<Category> findAll() {


        return categoryService.findAllByOrderByTitleAsc();

    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {


        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(categoryService.add(category));
    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {


        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Category category = null;

        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {


        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    //поиск по любым пораметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        String sortColumn = categorySearchValues.getSortColumn() != null ? categorySearchValues.getSortColumn() : null;
        String sortDirection = categorySearchValues.getSortDirection() != null ? categorySearchValues.getSortDirection() : null;

        Integer pageNumber = categorySearchValues.getPageNumber() != null ? categorySearchValues.getPageNumber() : null;
        Integer pageSize = categorySearchValues.getPageSize() != null ? categorySearchValues.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);

        Page result = categoryService.findByTitle(pageRequest);


        return ResponseEntity.ok(result);
    }

}
