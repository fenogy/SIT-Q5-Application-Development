package com.prabash.expensetracker.service;


import com.prabash.expensetracker.domain.Expense;
import com.prabash.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository) {
        this.expenseRepository = repository;
    }

    @Override
    public List<Expense> getExpensesByUserAndCategory(Integer userId, Integer categoryId) {
        return expenseRepository.findExpenseByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public Expense getExpenseByUserCategoryAndId(Integer userId, Integer categoryId, Integer expenseId) {
        return expenseRepository.findExpenseByUserIdAndCategoryIdAndExpenseId(userId, categoryId, expenseId);
    }

    @Override
    public Expense addExpense(Integer userId, Integer categoryId, Double amount, String name, String expenseDate) {
        Expense expense = new Expense();
        expense.setUserId(userId);
        expense.setCategoryId(categoryId);
        expense.setAmount(amount);
        expense.setDescription(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = formatter.parse(expenseDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        expense.setExpenseDate(date);
        return expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(Integer userId, Integer categoryId, Integer expenseId, Expense expense) {
        Expense expense1 = expenseRepository.findExpenseByUserIdAndCategoryIdAndExpenseId(userId, categoryId, expenseId);
        expense1.setExpenseDate(expense.getExpenseDate());
        expense1.setDescription(expense.getDescription());
        expense1.setAmount(expense.getAmount());
        expense1.setCategoryId(categoryId);
        expense1.setExpenseId(expenseId);
        expense1.setUserId(userId);
        expenseRepository.save(expense1);
    }

    @Override
    public void deleteExpense(Integer userId, Integer categoryId, Integer expenseId) {
        Expense expense1 = expenseRepository.findExpenseByUserIdAndCategoryIdAndExpenseId(userId, categoryId, expenseId);
        expenseRepository.delete(expense1);

    }
}
