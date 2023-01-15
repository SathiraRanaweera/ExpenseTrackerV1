package com.expense.tracker.repository;

import com.expense.tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    List<Transaction> findByUserAndCategoryIdAndDateStampBetween(long id, long id1, LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);
}