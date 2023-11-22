package com.youcode.digitalorders.common.helper;

import com.youcode.digitalorders.core.dao.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationHelper {
    public Optional<User> authenticate(User user, String password) {
        if (user.getPassword().equals(password)) {
            user.setIsAuthenticated(true);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
