package com.example.akedemyTodo.repo;

import com.example.akedemyTodo.entity.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {
}
