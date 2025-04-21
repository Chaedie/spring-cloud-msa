package com.example.userservice.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, max = 50, message = "Email must be 2 ~ 50 characters")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 20, message = "Password must be 8 ~ 20 characters")
    private String password;
}
