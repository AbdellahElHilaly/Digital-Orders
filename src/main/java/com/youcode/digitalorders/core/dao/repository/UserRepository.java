package com.youcode.digitalorders.core.dao.repository;

import com.youcode.digitalorders.core.dao.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
