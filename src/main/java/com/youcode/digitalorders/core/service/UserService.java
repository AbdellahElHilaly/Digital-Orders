package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService {

    public User save(User user);
    public User findByIdOrThrow(UUID id);
    public User findByEmailOrThrow(String email);
    public void deleteById(UUID id);
    public User update(UUID id, User user);
    public List<User> findAll();
    void login(String email, String password);
    void logout(String email);

}
