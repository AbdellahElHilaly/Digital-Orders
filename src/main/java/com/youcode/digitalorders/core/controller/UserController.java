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
        return userService.findByIdOrThrow(id);
    }

    @PostMapping
    public User insert(@RequestBody @Valid  UserDto userDto) {
        return userService.save(userDto.toEntity());
    }

    @PutMapping("/{id}")
    public User update(@RequestBody @Valid UserDto userDto, @PathVariable UUID id) {
        return userService.update(id, userDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }

    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password) {
        userService.login(email, password);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String email) {
        userService.logout(email);
    }



}
