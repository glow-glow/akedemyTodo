package com.example.akedemyTodo.DTO;

import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.UUID;
/**
 * слой ДТО для категорий
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TaskDTO  {
    private UUID id;
    private String title;
    private Integer completed;
    private Date date;

    private Boolean stat = false;
    private Integer priority;
    private Category category;
    public TaskDTO(UUID id, String title, Integer completed, Date date, Boolean stat, Integer priority, Category category) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.date = date;
        this.stat = stat;
        this.priority = priority;
        this.category = category;

    }
}
