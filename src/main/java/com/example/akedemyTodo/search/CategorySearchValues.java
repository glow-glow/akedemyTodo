package com.example.akedemyTodo.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * список что нужна получать для постраничности в категории
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySearchValues {
    private String text;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;
}
