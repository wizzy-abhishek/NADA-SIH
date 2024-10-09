package com.ai.aiml10.controller;

import com.ai.aiml10.dto.LoginDTO;
import com.ai.aiml10.dto.LoginResponseDTO;
import com.ai.aiml10.dto.SignUpDTO;
import com.ai.aiml10.dto.UserDTO;
import com.ai.aiml10.service.AuthLoginService;
import com.ai.aiml10.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService ;
    private final AuthLoginService authLoginService ;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO signUpDTO){
        return ResponseEntity.ok(userService.signUp(signUpDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO , HttpServletResponse response){

        LoginResponseDTO loginResponseDTO = authLoginService.login(loginDTO);

        Cookie cookie = new Cookie("refreshToken" , loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
       /* cookie.setSecure(false); //Make true when we have https*/
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(HttpServletRequest request){

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found"));

        LoginResponseDTO loginResponseDTO = authLoginService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
