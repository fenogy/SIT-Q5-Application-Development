package com.prabash.expensetracker.service;

import com.prabash.expensetracker.domain.User;

public interface UserService {
    User validateUser(String email, String password);

    User registerUser(String firstName, String lastName, String email, String password);
}
