package com.prabash.expensetracker.service;

import com.prabash.expensetracker.domain.User;
import com.prabash.expensetracker.exception.ETAuthException;
import com.prabash.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User validateUser(String email, String password) throws ETAuthException {
        if (email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws ETAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if (!pattern.matcher(Objects.requireNonNull(email)).matches())
            throw new ETAuthException("Invalid email format");
        Integer count = userRepository.countByEmail(email);
        if (count > 0)
            throw new ETAuthException("Email already in use");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        User returnUser = new User();
        try {
             returnUser =  userRepository.save(user);
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return returnUser;
    }
}
