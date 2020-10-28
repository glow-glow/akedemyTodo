package com.example.akedemyTodo.service;


import com.example.akedemyTodo.entity.Task;
import com.example.akedemyTodo.repo.TaskRepository;
import liquibase.pro.packaged.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task changeStat(Long id) {
        Task stat =findById(id);
        if (stat != null) {
            stat.setStat(!stat.getStat());
            repository.save(stat);
            return stat;
        }
        return null;

    }


    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task add(Task task) {
        return repository.save(task); // метод save обновляет или создает новый объект, если его не было
    }

    public Task update(Task task){
        return repository.save(task); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }


    public Page findByParams(String text, Integer completed,Long categoryId, PageRequest paging,UUID id,Boolean stat){
        return repository.findByParams(text, completed, categoryId, stat,id,paging);
    }

    public Task findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }
   public List<Task> getAllTodoTaskForId(UUID id){
        return repository.findById(id);
    }


}
