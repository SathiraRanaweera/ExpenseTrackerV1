package com.expense.tracker.service;

import com.expense.tracker.dto.CategoryDTO;
import com.expense.tracker.model.Category;
import com.expense.tracker.model.CategoryCustom;
import java.util.List;

public interface CategoryService {
    String addCategory(CategoryCustom category, Long userId);
    String updateCategory(CategoryDTO category, Long userId);
    String deleteCategory(Long categoryId, Long userId);
    List<CategoryDTO> getCategoriesByUser(Long userId);
}
