package com.example.akedemyTodo.mappers;

import com.example.akedemyTodo.DTO.CategoryDTO;
import com.example.akedemyTodo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
/**
 * мапер категорий (доставка для слоя ДТО категории)
 *автор алкесей
 */
@Mapper(componentModel = "spring")
@ComponentScan(basePackages = {"com.example.*"})
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO categoryDTO);
}
