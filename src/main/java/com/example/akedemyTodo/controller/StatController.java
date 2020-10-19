package com.example.akedemyTodo.controller;


import com.example.akedemyTodo.entity.Stat;

import com.example.akedemyTodo.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    private final StatService statService;


    public StatController(StatService statService) {
        this.statService = statService;
    }

    private final Long defaultId = 1l; // l - чтобы тип числа был Long, иначе будет ошибка компиляции


    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {


        return  ResponseEntity.ok(statService.findById(defaultId));
    }
}
