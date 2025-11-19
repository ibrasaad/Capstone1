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
    @Size(min = 7, message = "password length must be more than 6")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "password must contain letters and digits")    private String password;
    @NotEmpty(message = "email should be not empty")
    @Email(message = "email should be valid")
    private String email;
    @NotEmpty(message = "role should be not empty")
    @Pattern(regexp = "^(Admin|Customer)$",message = "role should be Admin or Customer")
    private String role;
    @NotNull(message = "balance should be valid")
    @Positive(message = "balance must be positive")
    private double balance;


}
