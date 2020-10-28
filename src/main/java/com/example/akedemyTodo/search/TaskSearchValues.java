package com.example.akedemyTodo.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchValues {
    private String title;
    private Integer completed;
    private Long categoryId;
    private UUID id;

    //построничность
    private Integer pageNumber;
    private Integer pageSize;

    //сортировка
    private String sortColumn;
    private String sortDirection;
    private Boolean stat;
}
