package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.entity.Task;
import com.example.akedemyTodo.repo.TaskRepository;
import com.example.akedemyTodo.search.TaskSearchValues;
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
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        // проверка на обязательные параметры
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        // если передали пустое значение title
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        // save работает как на добавление, так и на обновление
        taskRepository.save(task);

        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    // получение объекта по id
    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = null;

        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
        try {
            task = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {


        // исключить NullPointerException
        String text = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;

        // конвертируем Boolean в Integer
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;

        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;


        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection= taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;


        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;


        Sort.Direction direction =sortDirection == null || sortDirection.trim().length()==0 ||equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC;


        //подставляем все значения
        //объект сортировки
        Sort sort = Sort.by(direction,sortColumn);

        // объект построничности
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        //результат запроса
        Page result = taskRepository.findByParams(text, completed, priorityId, categoryId, pageRequest);
        // результат запроса
        return ResponseEntity.ok(result);

    }


}