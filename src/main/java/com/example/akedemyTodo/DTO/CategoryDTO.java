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

    private Long id;
    private String title;
    private Date date;

    private Long completedCount;
    private Long uncompletedCount;


}
