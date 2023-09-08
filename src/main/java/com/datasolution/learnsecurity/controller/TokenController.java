package com.datasolution.learnsecurity.controller;

import com.datasolution.learnsecurity.config.JwtProvider;
import com.datasolution.learnsecurity.dto.TokenDTO;
import com.datasolution.learnsecurity.entity.User;
import com.datasolution.learnsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    // access 토큰 만료, refresh 토큰 만료X 시 access 토큰 재발급
    @GetMapping("/refresh/access")
    public TokenDTO refreshAccessToken(HttpServletRequest request) {
        TokenDTO tokenDto = jwtProvider.resolveToken(request);

        if (!jwtProvider.validateToken(tokenDto.getAccessToken()) && jwtProvider.validateToken(tokenDto.getRefreshToken())) {
            User user = userService.getUserByUserId(jwtProvider.getUserId(tokenDto.getRefreshToken()));
            tokenDto.setAccessToken(jwtProvider.createToken(user.getUserId(), user.getRoles()));
        }

        return tokenDto;
    }

    // access 토큰 만료X refresh 토큰 만료 시 refresh 토큰 재발급
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/refresh/refresh")
    public TokenDTO refreshRefreshToken(HttpServletRequest request) {
        TokenDTO tokenDTO = jwtProvider.resolveToken(request);

        if (!jwtProvider.validateToken(tokenDTO.getRefreshToken()) && jwtProvider.validateToken(tokenDTO.getAccessToken())) {
            tokenDTO.setRefreshToken(jwtProvider.createRefreshToken(tokenDTO.getAccessToken()));
        }
        return tokenDTO;
    }

}
