package com.youcode.digitalorders.core.service.impl;

import com.youcode.digitalorders.common.helper.AuthenticationHelper;
import com.youcode.digitalorders.core.dao.model.entity.User;
import com.youcode.digitalorders.core.dao.repository.UserRepository;
import com.youcode.digitalorders.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public User save(User user) {
        checkIfResourceExist(user);

        return userRepository.save(user);
    }


    @Override
    public User findByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(UUID id, User user) {
        user.setId(findByIdOrThrow(id).getId());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void login(String email, String password) {
        User user = findByEmailOrThrow(email);

        if(user.getIsAuthenticated()) {
            throw new IllegalArgumentException("User is already authenticated");
        }
        else if (authenticationHelper.authenticate(user, password).isPresent()) {
            user.setIsAuthenticated(true);
            userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    @Override
    public void logout(String email) {
        User user = findByEmailOrThrow(email);
        if (user.getIsAuthenticated()) {
            user.setIsAuthenticated(false);
            userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("User is not authenticated");
        }
    }

    private void checkIfResourceExist(User user) {

        String errors = "";
        if(userRepository.existsByEmail(user.getEmail()))
            errors += "user email already exists";

        if(userRepository.existsByName(user.getName()))
            errors += "; user name already exists";

        if(!errors.isEmpty()) {
            System.out.println(errors);
            throw new DataIntegrityViolationException(errors);
        }
    }

    @Override
    public User findByEmailOrThrow(String email) {
        if(email == null || email.isEmpty()) throw new IllegalArgumentException("Invalid user email : " + email);
        return userRepository.findByEmail(email).orElseThrow(() -> {
            return new IllegalArgumentException("Invalid user email : " + email);
        });
    }
}

