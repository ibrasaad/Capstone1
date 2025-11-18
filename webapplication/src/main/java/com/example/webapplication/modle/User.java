package com.example.webapplication.modle;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id should be not empty")
    private String id;
    @NotEmpty(message = "username should be not empty")
    @Size(min = 6,message = "username length more than 5 ")
    private String username;
    @NotEmpty(message = "password should be not empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String password;
    @NotEmpty(message = "email should be not empty")
    @Email(message = "email should be valid")
    private String email;
    @Pattern(regexp = "^(Admin|Customer)$")
    private String role;
    @NotNull(message = "balance should be valid")
    @Positive
    private double balance;


}
