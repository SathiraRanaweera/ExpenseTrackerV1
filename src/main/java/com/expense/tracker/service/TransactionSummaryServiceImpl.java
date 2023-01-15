package com.expense.tracker.service;

import com.expense.tracker.dto.ApiResponse;
import com.expense.tracker.dto.CategoryDTO;
import com.expense.tracker.model.*;
import com.expense.tracker.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionSummaryServiceImpl implements TransactionSummaryService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionSummaryRepository transactionSummaryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionSummaryServiceImpl.class);

    @Override
    public ApiResponse getTransactionSummariesByUser(Long userId) {
        List<TransactionSummary> transactionSummaries = transactionSummaryRepository.findByUserIdOrderByCreatedAtDesc(userId);

        if(transactionSummaries.isEmpty()) {
            LOGGER.warn("There are no records for transaction summaries for user - {}", userId);
            return new ApiResponse(false, HttpStatus.NOT_FOUND.value(), "There are no records for transaction summaries");
        }
        LOGGER.info("Successfully get the all transaction summaries. (By User - {})", userId);
        return new ApiResponse(true, HttpStatus.OK.value(), transactionSummaries);
    }

    @Scheduled(cron = "* */5 * * * *")
//    @Scheduled(cron = "0 0 0 L * ? *")
    private void summarizeTransactions() {
        try {
            LOGGER.info("Scheduler is running for summarize the transactions");

            LocalDate lastDayOfMonth = LocalDate.now();
            LocalDate firstDayOfMonth = lastDayOfMonth.withDayOfMonth(1);
            String month = lastDayOfMonth.getMonth().name();

            List<User> users = userRepository.findAll();
            if(users.isEmpty()) {
                LOGGER.warn("There are no users to summarize the transactions");
            }
            for (User user : users) {
                ApiResponse categories = categoryService.getCategoriesByUser(user.getId());
                if(!categories.getSuccess()) {
                    LOGGER.warn("There are no categories to user - {} for summarize the transactions", user.getId());
                    break;
                }
                List<CategoryDTO> categoryDTOS = (List<CategoryDTO>) categories.getData();
                for (CategoryDTO categoryDTO : categoryDTOS) {
                    List<Transaction> transactions = transactionRepository.findByUserAndCategoryIdAndDateStampBetween(user.getId(), categoryDTO.getId(), firstDayOfMonth, lastDayOfMonth);
                    if(transactions.isEmpty()) {
                        LOGGER.warn("There are no transactions to user - {} and category - {} for summarize the transactions", user.getId(), categoryDTO.getId());
                        break;
                    }
                    Category category = categoryRepository.getReferenceById(categoryDTO.getId());
                    Budget budget = budgetRepository.findByUserIdAndCategoryId(user.getId(), categoryDTO.getId());
                    double totalTransactionAmount = 0;
                    for(Transaction transaction : transactions) {
                        totalTransactionAmount += transaction.getAmount();
                    }
                    TransactionSummary transactionSummary = new TransactionSummary(user, category, month, totalTransactionAmount, budget.getAmount());
                    transactionSummaryRepository.save(transactionSummary);
                    LOGGER.info("Successfully summarize the transactions of user - {} and category - {}", user.getId(), categoryDTO.getId());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Unable to summarize the transactions from scheduler");
            e.printStackTrace();
        }
    }
}
