package com.expense.tracker.controller;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.TransactionDTO;
import com.expense.tracker.model.Transaction;
import com.expense.tracker.service.TransactionService;
import com.expense.tracker.service.TransactionSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/expense_tracker/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionSummaryService transactionSummaryService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO transaction, @PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), transactionService.addTransaction(transaction, userId)));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction transaction, @PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), transactionService.updateTransaction(transaction, userId)));
    }

    @GetMapping("/delete/{userId}/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable long userId, @PathVariable long transactionId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), transactionService.deleteTransaction(transactionId, userId)));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getTransactionByUser(@PathVariable long userId) {
        return ResponseEntity.ok(transactionService.getTransactionByUser(userId));
    }

    @GetMapping("/get_summary/{userId}")
    public ResponseEntity<?> getTransactionSummariesByUser(@PathVariable long userId) {
        return ResponseEntity.ok(transactionSummaryService.getTransactionSummariesByUser(userId));
    }
}