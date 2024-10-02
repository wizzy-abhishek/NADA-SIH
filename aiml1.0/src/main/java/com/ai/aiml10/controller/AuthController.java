package com.ai.aiml10.controller;

import com.ai.aiml10.dto.LoginDTO;
import com.ai.aiml10.dto.SignUpDTO;
import com.ai.aiml10.dto.UserDTO;
import com.ai.aiml10.service.AuthLoginService;
import com.ai.aiml10.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO , HttpServletResponse response){
        String token = authLoginService.login(loginDTO);

        Cookie cookie = new Cookie("token" , token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
