package com.expense.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TransactionSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="user", nullable=false)
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name="category", nullable=false)
    @JsonIgnore
    private Category category;
    private String month;
    private double transactionAmount;
    private double budgetAmount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TransactionSummary(User user, Category category, String month, double transactionAmount, double budgetAmount) {
        this.user = user;
        this.category = category;
        this.month = month;
        this.transactionAmount = transactionAmount;
        this.budgetAmount = budgetAmount;
    }
}