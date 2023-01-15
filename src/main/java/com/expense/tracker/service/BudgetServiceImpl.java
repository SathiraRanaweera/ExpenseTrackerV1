package com.expense.tracker.service;

import com.expense.tracker.dto.BudgetDTO;
import com.expense.tracker.model.*;
import com.expense.tracker.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetServiceImpl.class);

    @Override
    public String addBudget(BudgetDTO budget, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Category category = categoryRepository.getReferenceById(budget.getCategoryId());
        Budget newBudget = new Budget(user, category, budget.getAmount());

        Budget savedBudget = budgetRepository.save(newBudget);
        LOGGER.info("Successfully added new budget - {}. (By User - {})", savedBudget.getId(), userId);
        return "Successfully added new budget";
    }

    @Override
    public String updateBudget(BudgetDTO budget, Long userId) {
        Budget updatingBudget = budgetRepository.getReferenceById(budget.getId());
        Category category = categoryRepository.getReferenceById(budget.getCategoryId());
        updatingBudget.setCategory(category);
        updatingBudget.setAmount(budget.getAmount());
        Budget savedBudget = budgetRepository.save(updatingBudget);
        LOGGER.info("Successfully updated the budget - {}. (By User - {})", savedBudget.getId(), userId);
        return "Successfully updated the budget";
    }

    @Override
    public String deleteBudget(Long budgetId, Long userId) {
        Budget budget = budgetRepository.getReferenceById(budgetId);
        budgetRepository.delete(budget);
        LOGGER.info("Successfully deleted the budget - {}. (By User - {})", budgetId, userId);
        return "Successfully deleted the budget";
    }

    @Override
    public List<BudgetDTO> getBudgetsByUser(Long userId) {
        List<Budget> budgets = budgetRepository.findAllByUserId(userId);
        List<BudgetDTO> budgetDTOS = budgets.stream().map(x -> new BudgetDTO(x.getId(), x.getCategory().getId(), x.getAmount())).collect(Collectors.toList());
        LOGGER.info("Successfully get the all budgets. (By User - {})", userId);
        return budgetDTOS;
    }
}
