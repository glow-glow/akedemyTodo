package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.entity.Priority;
import com.example.akedemyTodo.repo.PriorityRepository;
import com.example.akedemyTodo.search.CategorySearchValues;
import com.example.akedemyTodo.search.PrioritySearchValues;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ComponentScan(basePackages = {"com.example.*"})
@RequestMapping ("/priority")
public class PriorityController {

    private final PriorityRepository priorityRepository;


    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @GetMapping("/all")
    public List<Priority> findAll() {

        return  priorityRepository.findAllByOrderByIdAsc();

    }
    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){

        // проверка на обязательные параметры
        if (priority.getId() != null && priority.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(priorityRepository.save(priority));
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Priority> findById(@PathVariable Long id){
        Priority priority = null;

        try{
            priority = priorityRepository.findById(id).get();

        }catch (NoSuchElementException e){
            e.printStackTrace();
            return  new ResponseEntity("id=" + id + "not found",HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priority);
    }


    @DeleteMapping("/delete/id")
    public  ResponseEntity delete(@PathVariable Long id){
        try {
            priorityRepository.deleteById(id);

        }catch (EmptyStackException e){
            e.printStackTrace();
            return new ResponseEntity("id=" +id+"not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return  new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues){

        //если вместо текста пусто вернутся все категории
        return ResponseEntity.ok(priorityRepository.findByTitle(prioritySearchValues.getText()));
    }


}
