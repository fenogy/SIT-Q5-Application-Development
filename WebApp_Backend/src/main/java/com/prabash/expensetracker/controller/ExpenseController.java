package com.prabash.expensetracker.controller;

import com.prabash.expensetracker.domain.Expense;
import com.prabash.expensetracker.service.ExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category/{categoryId}/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService transactionService) {
        this.expenseService = transactionService;
    }

    @GetMapping("")
    public ResponseEntity<List<Expense>> getAllTransactions(HttpServletRequest request,
                                                            @PathVariable("categoryId") Integer categoryId) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<Expense> expenses = expenseService.getExpensesByUserAndCategory(userId, categoryId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Expense> getTransactionById(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @PathVariable("transactionId") Integer expenseId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Expense expenses = expenseService.getExpenseByUserCategoryAndId(userId, categoryId, expenseId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Expense> addTransaction(HttpServletRequest request,
                                                  @PathVariable("categoryId") Integer categoryId,
                                                  @RequestBody Map<String, Object> expenseMap) {
        Integer userId = (Integer) request.getAttribute("userId");
        Double amount = Double.valueOf(expenseMap.get("amount").toString());
        String name = (String) expenseMap.get("name");
        String expenseDateStr = (String) expenseMap.get("expenseDate");
        Expense expense = expenseService.addExpense(userId, categoryId, amount, name, expenseDateStr);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer expenseId,
                                                                  @RequestBody Expense expense) {
        Integer userId = (Integer) request.getAttribute("userId");
        expenseService.updateExpense(userId, categoryId, expenseId, expense);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer expenseId) {
        Integer userId = (Integer) request.getAttribute("userId");
        expenseService.deleteExpense(userId, categoryId, expenseId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
