package com.ai.aiml10.service;

import com.ai.aiml10.dto.LoginDTO;
import com.ai.aiml10.dto.LoginResponseDTO;
import com.ai.aiml10.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthLoginService {

    private final AuthenticationManager authenticationManager ;
    private final JWTService jwtService ;
    private final UserService userService ;

    public AuthLoginService(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Transactional
    public LoginResponseDTO login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail() , loginDTO.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(user.getId() , refreshToken , accessToken) ;
    }

    @Transactional
    public LoginResponseDTO refreshToken(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);
        UserEntity user = userService.findUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDTO(user.getId() , refreshToken , accessToken);
    }
}
