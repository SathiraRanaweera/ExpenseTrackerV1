package com.expense.tracker.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private long id;
    private String username;
    private String password;
}