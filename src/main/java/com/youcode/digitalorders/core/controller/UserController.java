package com.youcode.digitalorders.core.controller;

import com.youcode.digitalorders.core.dao.model.dto.UserDto;
import com.youcode.digitalorders.core.dao.model.entity.User;
import com.youcode.digitalorders.core.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.youcode.digitalorders.shared.Const.AppEndpoints.USER_ENDPOINT;

@RestController
@RequestMapping(USER_ENDPOINT)
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping
    public List<User> selectAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User selectById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping
    public User insert(@RequestBody @Valid  UserDto userDto) {
        return userService.save(userDto.toEntity());
    }





}
