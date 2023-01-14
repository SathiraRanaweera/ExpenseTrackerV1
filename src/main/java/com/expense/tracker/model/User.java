package com.expense.tracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<CategoryCustom> categoryCustomList;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<Transaction> transactions;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<TransactionSummary> transactionSummaries;
}