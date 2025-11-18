package com.example.webapplication.modle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = " ID Must not be empty")
    private String id ;
    @NotEmpty(message = "Prouduct ID must not be empty")
    private String prouductid;
    @NotEmpty(message = "Merch ID must not be empty")
    private String merchantid;
    @NotNull
    private int stock;

}
