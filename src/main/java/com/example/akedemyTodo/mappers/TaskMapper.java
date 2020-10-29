package com.example.akedemyTodo.mappers;

import com.example.akedemyTodo.DTO.TaskDTO;
import com.example.akedemyTodo.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;

/**
 * мапер задач (доставка для слоя ДТО задач)
 *автор алкесей
 */
@Mapper(componentModel = "spring")
@ComponentScan(basePackages = {"com.example.*"})
public interface TaskMapper  {
    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
}
