package com.example.webapplication.modle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
@NotEmpty
private String id ;
@NotEmpty
@Size(max = 3 , message = "name length must be below than 3")
private String name ;

}
