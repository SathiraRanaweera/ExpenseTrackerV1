package com.expense.tracker.service;

import com.expense.tracker.model.User;
import com.expense.tracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String addUser(User user) {
        User savedUser = userRepository.save(user);
        LOGGER.info("Successfully added new user - {}", savedUser.getId());
        return "Successfully added new user";
    }
}
