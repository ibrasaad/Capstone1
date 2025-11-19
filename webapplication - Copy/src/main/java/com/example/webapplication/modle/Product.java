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
@NotEmpty(message = "id must be not empty")
    private String id;
@NotEmpty(message = "name must be not empty")
@Size(min = 4,message = "name size must be larger than 3")
    private  String name;
@NotNull(message = "price must be not null")
@Positive(message = "price must be positive")
    private double price ;
@NotNull(message = "catgoryid must be not null")
   private String categoryid ; // String id of Category
}
