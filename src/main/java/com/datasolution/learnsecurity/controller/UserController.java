package com.datasolution.learnsecurity.controller;

import com.datasolution.learnsecurity.config.JwtProvider;
import com.datasolution.learnsecurity.dto.LoginRequest;
import com.datasolution.learnsecurity.dto.UserRequest;
import com.datasolution.learnsecurity.entity.User;
import com.datasolution.learnsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @GetMapping("/{userId}")
    public User getUserByUserId(@PathVariable String userId) {
        return userService.getUserByUserId(userId);
    }

    @PostMapping("/sign")
    public User sign(UserRequest user) {
        System.out.println(user.toString());
        return userService.sign(user);
    }

    @PostMapping("/login")
    public String login(LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        User user = userService.getUserByUserId(userId);

        if (user == null) {
            throw new UsernameNotFoundException("로그인 실패 - 아이디가 존재하지 않음");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("로그인 실패 - 비밀번호가 다름");
        } else {
            User loginUser = userService.getUserByUserId(userId);
            return jwtProvider.createToken(loginUser.getUserId(), loginUser.getRoles());
        }
    }
}
