package com.prabash.expensetracker.repository;

import com.prabash.expensetracker.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findExpenseByUserIdAndCategoryId(Integer userId, Integer categoryId);

    Expense findExpenseByUserIdAndCategoryIdAndExpenseId(Integer userId, Integer categoryId, Integer expenseId);


}
