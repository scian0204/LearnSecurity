package com.datasolution.learnsecurity.service;

import com.datasolution.learnsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService {
    private final UserRepository userRepository;

    public UserDetails getUserByUserId(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUserId(username);
    }
}
