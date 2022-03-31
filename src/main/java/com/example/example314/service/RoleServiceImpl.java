package com.example.example314.service;

import com.example.example314.dao.RoleRepository;
import com.example.example314.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final EntityManager manager;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, EntityManager manager) {
        this.roleRepository = roleRepository;
        this.manager = manager;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> role = manager.createQuery("select  r from Role r where r.name=:name", Role.class)
                .setParameter("name", name);
        return role.getResultList().get(0);
    }
}
