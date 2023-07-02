package com.prabash.expensetracker.service;

import com.prabash.expensetracker.domain.Category;
import com.prabash.expensetracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    @Override
    public List<Category> getCategoriesByUser(Integer userId) {
        return categoryRepository.findByUserId(userId);
    }

    @Override
    public Category getCategoriesByUserAndId(Integer userId, Integer categoryId) {
        return categoryRepository.findByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public Category addCategory(Integer userId, String name, String description) {
        Category addCategory = new Category();
        addCategory.setUserId(userId);
        addCategory.setName(name);
        addCategory.setDescription(description);
        return categoryRepository.save(addCategory);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category category) {
        Category toUpdate = categoryRepository.findByUserIdAndCategoryId(userId, categoryId);
        toUpdate.setDescription(category.getDescription());
        toUpdate.setName(category.getName());
        toUpdate.setCategoryId(categoryId);
        toUpdate.setUserId(userId);
        categoryRepository.save(toUpdate);
    }

    @Override
    public void deleteCategory(Integer userId, Integer categoryId) {
        categoryRepository.removeById(userId, categoryId);
    }
}
