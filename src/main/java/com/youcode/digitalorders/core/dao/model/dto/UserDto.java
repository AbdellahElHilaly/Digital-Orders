package com.youcode.digitalorders.core.dao.model.dto;

import com.youcode.digitalorders.core.dao.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class UserDto {

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank(message = "email should not be blank")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(min = 8, max = 20, message = "password should be between 8 and 20 characters")
    private String password;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}


