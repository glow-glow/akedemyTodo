package com.example.akedemyTodo.controller;


import com.example.akedemyTodo.entity.Stat;
import com.example.akedemyTodo.service.StatService;
import liquibase.pro.packaged.L;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@ComponentScan(basePackages = {"com.example.*"})
public class StatController {
    private final StatService statService;


    public StatController(StatService statService) {
        this.statService = statService;
    }

    private  Long defaultId=1l; // l - чтобы тип числа был Long, иначе будет ошибка компиляции


    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {


        return  ResponseEntity.ok(statService.findById(defaultId));
    }
}
