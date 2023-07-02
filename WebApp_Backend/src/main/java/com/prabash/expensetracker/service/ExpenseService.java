package com.prabash.expensetracker.service;

import com.prabash.expensetracker.domain.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getExpensesByUserAndCategory(Integer userId, Integer categoryId);

    Expense getExpenseByUserCategoryAndId(Integer userId, Integer categoryId, Integer expenseId);

    Expense addExpense(Integer userId, Integer categoryId, Double amount, String name, String expenseDate);

    void updateExpense(Integer userId, Integer categoryId, Integer expenseId, Expense expense);

    void deleteExpense(Integer userId, Integer categoryId, Integer expenseId);
}
