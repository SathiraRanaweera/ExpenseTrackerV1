package com.expense.tracker.service;

import com.expense.tracker.dto.TransactionDTO;
import com.expense.tracker.model.Transaction;
import java.util.List;

public interface TransactionService {
    String addTransaction(TransactionDTO transaction, Long userId);
    String updateTransaction(Transaction transaction, Long userId);
    String deleteTransaction(Long transactionId, Long userId);
    List<Transaction> getTransactionByUser(Long userId);
}
