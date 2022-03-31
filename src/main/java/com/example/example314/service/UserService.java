package com.example.example314.service;

import com.example.example314.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByEmail(String email);
    void addUser(User user);
    void deleteUser(long id);
    User updateUser(long id);
}
