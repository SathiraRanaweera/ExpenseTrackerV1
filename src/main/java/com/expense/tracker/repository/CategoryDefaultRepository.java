package com.expense.tracker.repository;

import com.expense.tracker.model.CategoryDefault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDefaultRepository extends JpaRepository<CategoryDefault, Long> {
}