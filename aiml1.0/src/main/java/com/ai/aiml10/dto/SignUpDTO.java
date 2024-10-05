package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpDTO {

    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank
    @Size(min = 3 , message = "Password must be of at least 3 digits")
    private String password;

    @NotBlank(message = "Name cant be blank")
    private String name;

    @NotNull
    private Set<Role> roles ;

}
