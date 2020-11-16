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
    private Long id;
    private String title;
    private Integer completed;
    private Date date;

    private Boolean stat = false;
    private Integer priority;
    private Category category;

}
