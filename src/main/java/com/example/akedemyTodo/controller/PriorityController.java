package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.entity.Priority;
import com.example.akedemyTodo.repo.PriorityRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"com.example.*"})
@RequestMapping ("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;


    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @GetMapping("/test")
    public List<Priority> test() {

        List<Priority> list = priorityRepository.findAll();


        return list;

    }


}
