package com.expense.tracker.controller;

import com.example.expenseTracker.dto.ApiResponse;
import com.expense.tracker.model.User;
import com.expense.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/expense_tracker/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return ResponseEntity.ok(new ApiResponse(true, HttpStatus.OK.value(), userService.addUser(user)));
    }
}
