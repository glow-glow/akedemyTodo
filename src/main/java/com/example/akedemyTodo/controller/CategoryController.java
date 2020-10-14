package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.entity.Priority;
import com.example.akedemyTodo.repo.CategoryRepository;

import com.example.akedemyTodo.search.CategorySearchValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ComponentScan(basePackages = {"com.example.*"})
@RequestMapping("/category")
public class CategoryController {


    private final CategoryRepository categoryRepository;


    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/all")
    public List<Category> findAll() {

        return categoryRepository.findAllByOrderByTitleAsc();

    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {

        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = null;

        try {
            category = categoryRepository.findById(id).get();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            categoryRepository.deleteById(id);

        } catch (EmptyStackException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    //поиск по любым пораметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){

        //если вместо текста пусто вернутся все категории
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
    }


}
