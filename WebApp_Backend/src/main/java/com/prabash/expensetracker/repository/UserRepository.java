package com.prabash.expensetracker.repository;

import com.prabash.expensetracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);

    Integer countByEmail(String email);

}
