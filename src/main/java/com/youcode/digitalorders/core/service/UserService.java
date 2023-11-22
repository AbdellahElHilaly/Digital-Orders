package com.youcode.digitalorders.core.service;

import com.youcode.digitalorders.core.dao.model.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService {

    public User save(User user);
    public User findById(UUID id);
    public void deleteById(UUID id);
    public User update(UUID id, User user);
    public List<User> findAll();

}
