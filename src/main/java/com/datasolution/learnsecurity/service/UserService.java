package com.datasolution.learnsecurity.service;

import com.datasolution.learnsecurity.dto.UserRequest;
import com.datasolution.learnsecurity.entity.User;
import com.datasolution.learnsecurity.repository.RoleRepository;
import com.datasolution.learnsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User getUserByUserId(String userId) {
        return userRepository.getUserByUserId(userId);
    }

    public User sign(UserRequest user) {
        if (userRepository.existsByUserId(user.getUserId())) {
            return new User();
        } else if (!roleRepository.existsByRoleId(user.getRoleId())) {
            return new User();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user.convert());
    }
}
