package com.example.akedemyTodo.repo;

import com.example.akedemyTodo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // if title == null or =='' then all elements


    Page<Category> findByTitle(
                               Pageable pageable
    );
    List<Category> findAllByOrderByTitleAsc();// сортировка по названию
}
