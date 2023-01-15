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
public class Transaction {

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
    private LocalDate dateStamp;
    private double amount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Transaction(User user, Category category, LocalDate dateStamp, double amount) {
        this.user = user;
        this.category = category;
        this.dateStamp = dateStamp;
        this.amount = amount;
    }
}