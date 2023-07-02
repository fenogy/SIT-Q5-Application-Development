package com.prabash.expensetracker.repository;

import com.prabash.expensetracker.domain.Category;
import com.prabash.expensetracker.exception.ETResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserId(Integer userId) throws ETResourceNotFoundException;

    Category findByUserIdAndCategoryId(Integer userId, Integer categoryId) throws ETResourceNotFoundException;

    @Modifying
    @Query("delete from Expense e where e.userId = :userId and e.categoryId = :categoryId")
    void removeById(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId);

}
