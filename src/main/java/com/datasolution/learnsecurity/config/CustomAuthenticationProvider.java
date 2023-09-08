//package com.datasolution.learnsecurity.config;
//
//import com.datasolution.learnsecurity.entity.User;
//import com.datasolution.learnsecurity.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtProvider jwtProvider;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String userId = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//
//        User user = userService.getUserByUserId(userId);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("로그인 실패 - 아이디가 존재하지 않음");
//        } else if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new BadCredentialsException("로그인 실패 - 비밀번호가 다름");
//        } else {
//            User loginUser = userService.getUserByUserId(userId);
//            String token = jwtProvider.createToken(loginUser.getUserId(), loginUser.getRoles());
//            return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
