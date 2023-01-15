package com.expense.tracker.repository;

import com.expense.tracker.model.TransactionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionSummaryRepository extends JpaRepository<TransactionSummary, Long> {
    List<TransactionSummary> findByUserIdOrderByCreatedAtDesc(Long userId);
}