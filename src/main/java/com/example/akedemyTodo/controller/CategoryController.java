package com.example.akedemyTodo.controller;

import com.example.akedemyTodo.DTO.CategoryDTO;
import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.mappers.CategoryMapper;
import com.example.akedemyTodo.search.CategorySearchValues;
import com.example.akedemyTodo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * котнроллер который обробатывет запросы  связанные с категорией
 * автор алексей
 *
 */

import java.util.List;
@RequiredArgsConstructor
@RestController
@ComponentScan(basePackages = {"com.example.*"})
@RequestMapping("/category")
public class CategoryController {

    /**
     * Автозаполнение bean-a
     */
    private final CategoryService categoryService;
    private  final CategoryMapper categoryMapper;

    /**
     * метод который показывает все категории
     *
     */
    @GetMapping("/all")
    public List<Category> findAll() {


        return categoryService.findAllByOrderByTitleAsc();

    }
    /**
     * метод который добавляет категориюю
     */

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> add(@RequestBody CategoryDTO categoryDTO) {
        Category category =categoryMapper.toEntity(categoryDTO);
        category= categoryService.add(category);
        categoryDTO = categoryMapper.toDTO(category);


        // если передали пустое значение title
        if (categoryDTO.getTitle() == null || categoryDTO.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(categoryDTO,HttpStatus.CREATED);


    }
    /**
     * метод который обнавляет категориюю
     */

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CategoryDTO categoryDTO) {
            Category category =categoryMapper.toEntity(categoryDTO);
            category= categoryService.update(category);
            categoryDTO = categoryMapper.toDTO(category);


            // если передали пустое значение title
            if (categoryDTO.getTitle() == null || categoryDTO.getTitle().trim().length() == 0) {
                return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>(categoryDTO,HttpStatus.CREATED);


        }
    /**
     * метод который ищет категориюю по айди
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO =categoryMapper.toDTO(category);

        return ResponseEntity.ok(categoryDTO);
    }
    /**
     * метод который удаляет категорию по айди
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
    /**
     * постраничность с сортировкой размер страницы можно выбрать любой + сортировка
     */
    //поиск по любым пораметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        String sortColumn = categorySearchValues.getSortColumn() != null ? categorySearchValues.getSortColumn() : null;
        String sortDirection = categorySearchValues.getSortDirection() != null ? categorySearchValues.getSortDirection() : null;

        Integer pageNumber = categorySearchValues.getPageNumber() != null ? categorySearchValues.getPageNumber() : null;
        Integer pageSize = categorySearchValues.getPageSize() != null ? categorySearchValues.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);

        Page result = categoryService.findByTitle(pageRequest);


        return ResponseEntity.ok(result);
    }

}
