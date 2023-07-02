package com.prabash.expensetracker.service;

import com.prabash.expensetracker.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesByUser(Integer userId);

    Category getCategoriesByUserAndId(Integer userId, Integer categoryId);

    Category addCategory(Integer userId, String name, String description);

    void updateCategory(Integer userId, Integer categoryId, Category category);

    void deleteCategory(Integer userId, Integer categoryId);
}
