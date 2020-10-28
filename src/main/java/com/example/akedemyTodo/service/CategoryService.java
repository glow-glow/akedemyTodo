package com.example.akedemyTodo.service;

import com.example.akedemyTodo.entity.Category;
import com.example.akedemyTodo.repo.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }


    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category add(Category category) {
        return repository.save(category); // метод save обновляет или создает новый объект, если его не было
    }

    public Category update(Category category){
        return repository.save(category); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Page findByTitle(PageRequest paging ){
        return repository.findByTitle(paging);
    }

    public Category findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }

    public List<Category> findAllByOrderByTitleAsc(){
        return repository.findAllByOrderByTitleAsc();
    }



}

