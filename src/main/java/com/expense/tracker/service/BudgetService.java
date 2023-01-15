package com.expense.tracker.service;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.BudgetDTO;
import java.util.List;

public interface BudgetService {
    String addBudget(BudgetDTO budget, Long userId);
    String updateBudget(BudgetDTO budget, Long userId);
    String deleteBudget(Long budgetId, Long userId);
    ApiResponse getBudgetsByUser(Long userId);
}
