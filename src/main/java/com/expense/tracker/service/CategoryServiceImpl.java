package com.expense.tracker.service;

import com.expense.tracker.dto.CategoryDTO;
import com.expense.tracker.model.CategoryCustom;
import com.expense.tracker.model.CategoryDefault;
import com.expense.tracker.model.User;
import com.expense.tracker.repository.CategoryCustomRepository;
import com.expense.tracker.repository.CategoryDefaultRepository;
import com.expense.tracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDefaultRepository categoryDefaultRepository;
    @Autowired
    CategoryCustomRepository categoryCustomRepository;
    @Autowired
    UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public String addCategory(CategoryCustom category, Long userId) {
        User user = userRepository.getReferenceById(userId);
        category.setUser(user);
        category.setIsActive(true);
        category.setCreatedBy(userId);
        category.setUpdatedBy(userId);
        CategoryCustom categoryCustom = categoryCustomRepository.save(category);
        LOGGER.info("Successfully added new custom category - {}. (By User - {})", categoryCustom.getId(), userId);
        return "Successfully added new custom category";
    }

    @Override
    public String updateCategory(CategoryDTO category, Long userId) {
        CategoryCustom categoryCustom = categoryCustomRepository.getReferenceById(category.getId());
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        category.setTransactionType(category.getTransactionType());
        CategoryCustom savedCategoryCustom = categoryCustomRepository.save(categoryCustom);
        LOGGER.info("Successfully updated the custom category - {}. (By User - {})", savedCategoryCustom.getId(), userId);
        return "Successfully updated the custom category";
    }

    @Override
    public String deleteCategory(Long categoryId, Long userId) {
        CategoryCustom categoryCustom = categoryCustomRepository.getReferenceById(categoryId);
        categoryCustomRepository.delete(categoryCustom);
        LOGGER.info("Successfully deleted the custom category - {}. (By User - {})", categoryId, userId);
        return "Successfully deleted the custom category";
    }

    @Override
    public List<CategoryDTO> getCategoriesByUser(Long userId) {
        List<CategoryDefault> categoryDefaults = categoryDefaultRepository.findAll();
        List<CategoryDTO> defaultsToCustomDTO = categoryDefaults.stream().map(x -> new CategoryDTO(x.getId(), x.getName(), x.getDescription(), x.getTransactionType(), true)).collect(Collectors.toList());
        List<CategoryCustom> categoryCustoms = categoryCustomRepository.findAllByUserId(userId);
        List<CategoryDTO> customsToCustomDTO = categoryCustoms.stream().map(x -> new CategoryDTO(x.getId(), x.getName(), x.getDescription(), x.getTransactionType(), true)).collect(Collectors.toList());
        defaultsToCustomDTO.addAll(customsToCustomDTO);
        LOGGER.info("Successfully get the all categories. (By User - {})", userId);
        return defaultsToCustomDTO;
    }
}
