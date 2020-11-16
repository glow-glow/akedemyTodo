package com.example.akedemyTodo.repo;

import com.example.akedemyTodo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.UUID;

/**
 * Интерфейс класса Task
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // учитываем, что параметр может быть null или пустым
    @Query("SELECT p FROM Task p where " +
            "(:text is null or lower(p.title) like lower(concat('%', :text,'%'))) and" +
            "(:completed is null or p.completed=:completed) and " +
            "(:categoryId is null or p.category.id=:categoryId) and" +
            "(:stat is null or p.stat=:stat)"
    )
    /**
     * сортировка по всем столбцам постраничность
     */

    Page<Task> findByParams(@Param("text") String text,
                            @Param("date") Date date,
                            @Param("completed") Integer completed,
                            @Param("categoryId") Long categoryId,
                            @Param("stat") Boolean stat,
                            @Param("id") Long id,
                            Pageable pageable
    );

}