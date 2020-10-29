package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.DTO.TaskDTO;
import com.example.akedemyTodo.entity.Task;
import com.example.akedemyTodo.mappers.TaskMapper;
import com.example.akedemyTodo.search.TaskSearchValues;
import com.example.akedemyTodo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * котнроллер который обробатывет запросы  связанные с задачами
 * автор алексей
 *
 */
@RequiredArgsConstructor
@RestController
@ComponentScan(basePackages = {"com.example.*"})
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    // получение всех данных
    /**
     * метод который показывает все задачи в категории
     *
     */

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }


    /**
     * метод который добавляет задачу
     *
     */
    @PostMapping("/add")
    public ResponseEntity<TaskDTO> add(@RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        task= taskService.add(task);
        taskDTO = taskMapper.toDTO(task);
        if (taskDTO.getTitle() == null || taskDTO.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(taskDTO,HttpStatus.CREATED);


    }
    /**
     * метод который обнавляет задачу
     *
     */

    @PutMapping("/update")
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        task= taskService.update(task);
        taskDTO = taskMapper.toDTO(task);
        return new ResponseEntity<>(taskDTO,HttpStatus.CREATED);


    }

    /**
     * метод который удаляет задачу по айди
     *
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }
    /**
     * метод который ищет задачу по айди
     *
     */

    @GetMapping("/id/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {

        Task task = taskService.findById(id);
        TaskDTO taskDTO = taskMapper.toDTO(task);

        return ResponseEntity.ok(taskDTO);

    }
    /**
     * постраничность номер страницы и размер показываемого на ней выбриает щас + сортировка по всем столбцам
     *
     */
    // TaskSearchValues содержит все возможные параметры поиска
    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {



        // исключить NullPointerException
        String text = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;

        // конвертируем Boolean в Integer
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;

        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;
        Boolean stat  = taskSearchValues.getStat() != null ? taskSearchValues.getStat() : null;
        Date date  = taskSearchValues.getDate() != null ? taskSearchValues.getDate() : null;



        UUID id = taskSearchValues.getId() != null ? taskSearchValues.getId() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;


        // подставляем все значения

        // объект сортировки
        Sort sort = Sort.by(direction, sortColumn);

        // объект постраничности
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        // результат запроса с постраничным выводом

        Page result = taskService.findByParams(text,date,completed,categoryId,pageRequest,id,stat);

        // результат запроса
        return ResponseEntity.ok(result);

    }
    /**
     * метод который обнавляет статус задачи
     *
     */
    @PutMapping("/state/{id}")
    public ResponseEntity<TaskDTO> changeDoneStat(@PathVariable Long id) {

        Task task = taskService.changeStat(id);
        TaskDTO taskDTO = taskMapper.toDTO(task);
        return ResponseEntity.ok(taskDTO);
    }


}