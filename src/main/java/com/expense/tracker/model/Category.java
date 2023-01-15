package com.expense.tracker.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private Integer transactionType; // 1 - Dr, 2 - Cr
    private Boolean isActive;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long createdBy;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Long updatedBy;
    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    private Set<Budget> budgets;
    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    private Set<Transaction> transactions;
    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    private Set<TransactionSummary> transactionSummaries;
}