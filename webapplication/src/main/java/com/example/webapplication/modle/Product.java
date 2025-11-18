package com.example.webapplication.modle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
@NotEmpty
    private String id;
@NotEmpty
@Size(min = 4,message = "name size must be larger than 3")
    private  String name;
@NotNull
@Positive
    private double price ;
@NotNull
   private String categoryid ; // String id of Category
}
