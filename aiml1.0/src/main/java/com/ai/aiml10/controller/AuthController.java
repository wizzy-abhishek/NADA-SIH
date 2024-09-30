package com.ai.aiml10.controller;

import com.ai.aiml10.dto.LoginDTO;
import com.ai.aiml10.dto.SignUpDTO;
import com.ai.aiml10.dto.UserDTO;
import com.ai.aiml10.service.AuthLoginService;
import com.ai.aiml10.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService ;
    private final AuthLoginService authLoginService ;

    public AuthController(UserService userService, AuthLoginService authLoginService) {
        this.userService = userService;
        this.authLoginService = authLoginService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO signUpDTO){
        return ResponseEntity.ok(userService.signUp(signUpDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO){
        String token = authLoginService.login(loginDTO);
        return ResponseEntity.ok(token);
    }

}
