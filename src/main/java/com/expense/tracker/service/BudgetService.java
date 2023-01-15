package com.expense.tracker.service;

import com.expense.tracker.dto.BudgetDTO;
import java.util.List;

public interface BudgetService {
    String addBudget(BudgetDTO budget, Long userId);
    String updateBudget(BudgetDTO budget, Long userId);
    String deleteBudget(Long budgetId, Long userId);
    List<BudgetDTO> getBudgetsByUser(Long userId);
}
