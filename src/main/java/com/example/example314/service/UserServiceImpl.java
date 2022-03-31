package com.example.example314.service;

import com.example.example314.dao.RoleRepository;
import com.example.example314.dao.UserRepository;
import com.example.example314.model.Role;
import com.example.example314.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final EntityManager manager;
    private final PasswordEncoder encoderConfig;


    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, EntityManager manager,
                           PasswordEncoder encoderConfig) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.manager = manager;
        this.encoderConfig = encoderConfig;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = manager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email);
        List<User> result = query.getResultList();
        if (result.isEmpty()) {
//            throw new UsernameNotFoundException(String.format("User with email : %s", email));
            return null;
        }
        return result.get(0);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(encoderConfig.encode(user.getPassword()));
        User userFromDB = getUserByEmail(user.getUsername());
        Set<Role> baseRole = new HashSet<>();
        baseRole.add(roleRepository.getById(2L));
        if (userFromDB != null) {
            throw new IllegalArgumentException(String.format("User %s not found", user.getUsername()));
        }
        user.setRoles(baseRole);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(long id) {
        User userUpdate = userRepository.getById(id);
        return userRepository.save(userUpdate);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }
}
