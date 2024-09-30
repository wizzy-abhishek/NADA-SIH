package com.ai.aiml10.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpDTO {


    private String id ;

    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email ;

    @NotBlank
    @Size(min = 3 , message = "Password must be of at least 3 digits")
    private String password ;

    @NotBlank(message = "Name cant be blank")
    private String name ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank @Email(message = "Please provide a valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email(message = "Please provide a valid email") String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 3, message = "Password must be of at least 3 digits") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 3, message = "Password must be of at least 3 digits") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Name cant be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cant be blank") String name) {
        this.name = name;
    }
}
