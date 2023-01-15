package com.expense.tracker.service;

import com.expense.tracker.dto.ApiResponse;

public interface TransactionSummaryService {
    ApiResponse getTransactionSummariesByUser(Long userId);
}
