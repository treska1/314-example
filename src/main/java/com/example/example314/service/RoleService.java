package com.example.example314.service;

import com.example.example314.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleById(long id);
    Role getRoleByName(String Name);

}
