package com.example.akedemyTodo.repo;


import com.example.akedemyTodo.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
     List<Priority> findAllByOrderByIdAsc();//сортировка по id

}