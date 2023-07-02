package com.prabash.expensetracker.domain;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TB_ET_EXPENSE")
public class Expense {
    @Id
    @Column(name = "EXPENSE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer expenseId;
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;
    @Column(name = "USER_ID")
    private Integer userId;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;*/
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;*/
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EXP_TIMESTAMP")
    private Date expenseDate;

    public Expense(Integer expId, Integer categoryId, Integer userId, Double amount, String desc, Date expTs) {
        this.expenseId = expId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.amount = amount;
        this.description = desc;
        this.expenseDate = expTs;
    }

    public Expense() {

    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }
}
