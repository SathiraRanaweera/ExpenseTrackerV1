package com.expense.tracker.service;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.TransactionDTO;
import com.expense.tracker.model.Category;
import com.expense.tracker.model.Transaction;
import com.expense.tracker.model.User;
import com.expense.tracker.repository.CategoryRepository;
import com.expense.tracker.repository.TransactionRepository;
import com.expense.tracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public String addTransaction(TransactionDTO transaction, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Category category = categoryRepository.getReferenceById(transaction.getCategoryId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(transaction.getDateStamp(), formatter);
        Transaction newTransaction = new Transaction(user, category, localDate, transaction.getAmount());

        Transaction savedTransaction = transactionRepository.save(newTransaction);
        LOGGER.info("Successfully added new transaction - {}. (By User - {})", savedTransaction.getId(), userId);
        return "Successfully added new transaction";
    }

    @Override
    public String updateTransaction(Transaction transaction, Long userId) {
        Transaction savedtransaction = transactionRepository.save(transaction);
        LOGGER.info("Successfully updated the transaction - {}. (By User - {})", savedtransaction.getId(), userId);
        return "Successfully updated the transaction";
    }

    @Override
    public String deleteTransaction(Long transactionId, Long userId) {
        Transaction transaction = transactionRepository.getReferenceById(transactionId);
        transactionRepository.delete(transaction);
        LOGGER.info("Successfully deleted the transaction - {}. (By User - {})", transactionId, userId);
        return "Successfully deleted the transaction";
    }

    @Override
    public ApiResponse getTransactionByUser(Long userId) {
        List<Transaction> transactions = transactionRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        if(transactions.isEmpty()) {
            LOGGER.warn("There are no records for transaction for user - {}", userId);
            return new ApiResponse(false, HttpStatus.NOT_FOUND.value(), "There are no records for transactions");
        }
        LOGGER.info("Successfully get the all transactions. (By User - {})", userId);
        return new ApiResponse(true, HttpStatus.OK.value(), transactions);
    }
}
