package com.expense.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private Boolean success;
    private int status;
    private Object data;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
