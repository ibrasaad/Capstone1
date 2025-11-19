package com.example.webapplication.modle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
@NotEmpty(message = "name cant be empty")
    private String name;
@NotEmpty(message = "id cant be empty")
@Size(min = 4 ,message = "id length must be over 3")
    private String id;



}
