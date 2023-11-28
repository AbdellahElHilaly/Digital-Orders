package com.youcode.digitalorders.core.dao.model.entity;

import com.youcode.digitalorders.core.dao.model.dto.UserDto;
import jakarta.persistence.*;
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

    private String password;

    @Column(name = "is_authenticated", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isAuthenticated;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'USER'")
    private String role;

}
