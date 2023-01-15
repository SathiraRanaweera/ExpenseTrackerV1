package com.expense.tracker.controller;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.BudgetDTO;
import com.expense.tracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/expense_tracker/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addBudget(@RequestBody BudgetDTO budget, @PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), budgetService.addBudget(budget, userId)));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateBudget(@RequestBody BudgetDTO budget, @PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), budgetService.updateBudget(budget, userId)));
    }

    @GetMapping("/delete/{userId}/{budgetId}")
    public ResponseEntity<?> deleteBudget(@PathVariable long userId, @PathVariable long budgetId) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), budgetService.deleteBudget(budgetId, userId)));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getBudgetsByUser(@PathVariable long userId) {
        return ResponseEntity.ok(budgetService.getBudgetsByUser(userId));
    }
}