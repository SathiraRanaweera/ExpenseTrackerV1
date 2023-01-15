package com.expense.tracker.repository;

import com.expense.tracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findAllByUserId(Long userId);

    Budget findByUserIdAndCategoryId(long userId, long categoryId);
}