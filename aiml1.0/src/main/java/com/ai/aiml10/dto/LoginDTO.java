package com.ai.aiml10.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @Email
    private String email ;
    @Size(min = 3)
    private String password ;

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @Size(min = 3) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 3) String password) {
        this.password = password;
    }
}
