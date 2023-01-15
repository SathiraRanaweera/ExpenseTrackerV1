package com.expense.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {
    private long id;
    private String name;
    private String description;
    private Integer transactionType; // 1 - Dr, 2 - Cr
    private Boolean isDefault;
}