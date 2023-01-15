package com.expense.tracker.service;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.TransactionDTO;
import com.expense.tracker.model.Transaction;

public interface TransactionService {
    String addTransaction(TransactionDTO transaction, Long userId);
    String updateTransaction(Transaction transaction, Long userId);
    String deleteTransaction(Long transactionId, Long userId);
    ApiResponse getTransactionByUser(Long userId);
}
