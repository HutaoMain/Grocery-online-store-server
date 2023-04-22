package com.online.grocery.store.controller;

import com.online.grocery.store.dto.CategorySoldDto;
import com.online.grocery.store.model.Category;
import com.online.grocery.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/create")
    ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category setCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(setCategory);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Category>> getCategoryById(@PathVariable String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/bestCategories")
    ResponseEntity<List<CategorySoldDto>> getBestCategories() {
        List<CategorySoldDto> categorySoldDtoList = categoryService.getTopCategoriesBySold();
        return ResponseEntity.ok(categorySoldDtoList);
    }
}
