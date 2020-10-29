package com.example.akedemyTodo.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.sql.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
/**
 * слой ДТО для категорий
 *
 */
public class CategoryDTO  {

    private UUID id;
    private String title;
    private Date date;

    private Long completedCount;
    private Long uncompletedCount;

    public CategoryDTO(UUID id, String title, Date date, Long completedCount, Long uncompletedCount) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.completedCount = completedCount;
        this.uncompletedCount = uncompletedCount;
    }
}
