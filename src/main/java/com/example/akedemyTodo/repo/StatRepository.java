package com.example.akedemyTodo.repo;

import com.example.akedemyTodo.entity.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {
}
