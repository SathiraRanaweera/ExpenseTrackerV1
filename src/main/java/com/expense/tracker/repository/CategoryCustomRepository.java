package com.expense.tracker.repository;

import com.expense.tracker.model.CategoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCustomRepository extends JpaRepository<CategoryCustom, Long> {
}