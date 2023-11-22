package com.youcode.digitalorders.core.dao.model.dto;

import com.youcode.digitalorders.core.dao.model.entity.User;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public class UserDto {
    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank(message = "email should not be blank")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "address should not be blank")
    private String address;

    @NotNull(message = "age should not be null")
    @Min(value = 18, message = "age should be greater than 18")
    @Max(value = 60,message = "age should be less than 60")
    private Integer age;

    @NotNull(message = "salary should not be null")
    @Positive(message = "salary should be positive")
    @Min(value = 5000, message = "salary should be greater than 5000")
    private Double salary;
    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .address(this.address)
                .age(this.age)
                .salary(this.salary)
                .build();
    }
}
