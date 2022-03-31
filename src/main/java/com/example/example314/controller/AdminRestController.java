package com.example.example314.controller;


import com.example.example314.model.User;
import com.example.example314.service.RoleService;
import com.example.example314.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers (){
return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public void addUser(User user){
        userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUserById(@PathVariable long id){
        userService.deleteUser(id);
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable long id){
        return userService.updateUser(id);
    }
}
