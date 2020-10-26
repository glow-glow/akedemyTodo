package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.search.CategorySearchValues;
import com.example.akedemyTodo.service.CategoryService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Java-doc!!!
 */
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
    public ResponseEntity<Category> add(@RequestBody Category category){





        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(categoryService.add(category));
    }



    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category){




        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Category category = null;

        try{
            category = categoryService.findById(id);
        }catch (NoSuchElementException e){ // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return  ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable  Long id) {



        try {
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }
    //поиск по любым пораметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){



        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getText()));
    }


    // TODO(Шайдуко): не пойму как решен вопрос с пагинацией

}
