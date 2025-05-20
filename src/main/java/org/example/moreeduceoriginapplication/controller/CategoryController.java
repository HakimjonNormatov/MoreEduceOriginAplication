package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.CategoryDto;
import org.example.moreeduceoriginapplication.model.Category;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.create(categoryDto);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<?>put(@RequestBody CategoryDto categoryDto , @PathVariable Long id){
        Result result = categoryService.update(id , categoryDto);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?>delete(@PathVariable Long id){
        Result result = categoryService.delete(id);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
