package com.youcode.digitalorders.core.dao.model.entity;

import com.youcode.digitalorders.core.dao.model.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    private String address;
    private Integer age;
    private Double salary;

    public UserDto toDto() {
        return UserDto.builder()
                .name(this.name)
                .email(this.email)
                .address(this.address)
                .age(this.age)
                .salary(this.salary)
                .build();
    }


}
