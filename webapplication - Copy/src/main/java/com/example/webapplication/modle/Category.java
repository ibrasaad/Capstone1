package com.example.webapplication.modle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
@NotEmpty(message = " id must not empty")
private String id ;
@NotEmpty(message = "name must not empty")
@Size(max = 3 , message = "name length must be below than 3")
private String name ;

}
