package com.ai.aiml10.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDTO {

    private String id;

    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank
    @Size(min = 3 , message = "Password must be of at least 3 digits")
    private String password;

    @NotBlank(message = "Name cant be blank")
    private String name;

}
