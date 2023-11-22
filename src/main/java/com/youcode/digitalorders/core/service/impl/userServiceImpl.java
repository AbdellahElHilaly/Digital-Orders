package com.youcode.digitalorders.core.service.impl;

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


    @Override
    public User save(User user) {
        checkIfResourceExist(user);

        return userRepository.save(user);
    }


    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(UUID id, User user) {
        user.setId(findById(id).getId());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
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
}
