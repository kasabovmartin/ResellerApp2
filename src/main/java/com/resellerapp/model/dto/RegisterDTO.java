package com.resellerapp.model.dto;

import com.resellerapp.vallidation.annotations.UniqueEmail;
import com.resellerapp.vallidation.annotations.UniqueUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterDTO {

    @UniqueUsername
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
    private String username;
    @Email
    @UniqueEmail
    @Size(min = 2, max = 20, message = "Please enter a valid email longer than 5 characters")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
}
