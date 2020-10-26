package com.example.akedemyTodo.service;

import com.example.akedemyTodo.entity.Stat;
import com.example.akedemyTodo.repo.StatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * TODO: не нужен!!!
 */
@Service
@Transactional
public class StatService {
    private final StatRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findById(Long id){
        return repository.findById(id).get();
    }

}
