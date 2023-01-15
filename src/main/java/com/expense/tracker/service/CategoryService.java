package com.expense.tracker.service;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.CategoryDTO;
import com.expense.tracker.model.CategoryCustom;

public interface CategoryService {
    String addCategory(CategoryCustom category, Long userId);
    String updateCategory(CategoryDTO category, Long userId);
    String deleteCategory(Long categoryId, Long userId);
    ApiResponse getCategoriesByUser(Long userId);
}
