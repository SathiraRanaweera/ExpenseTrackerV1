package com.expense.tracker.controller;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.CategoryDTO;
import com.expense.tracker.model.CategoryCustom;
import com.expense.tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/expense_tracker/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addCategory(@RequestBody CategoryCustom category, @PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), categoryService.addCategory(category, userId)));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO category, @PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), categoryService.updateCategory(category, userId)));
    }

    @GetMapping("/delete/{userId}/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable long userId, @PathVariable long categoryId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), categoryService.deleteCategory(categoryId, userId)));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getCategoriesByUser(@PathVariable long userId) {
        return ResponseEntity.ok(categoryService.getCategoriesByUser(userId));
    }
}