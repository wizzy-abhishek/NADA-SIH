package com.ai.aiml10.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private String id;

    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Name cant be blank")
    private String name;

}
