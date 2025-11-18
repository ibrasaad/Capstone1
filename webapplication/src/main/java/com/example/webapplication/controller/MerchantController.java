package com.example.webapplication.controller;

import com.example.webapplication.modle.Merchant;
import com.example.webapplication.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor()
public class MerchantController {
    private final MerchantService merchantService;


    @GetMapping("/get")
    public ResponseEntity<?>getallMerchant(){
        return ResponseEntity.status(200).body(merchantService.getMerchantslist());
    }
    @PostMapping("/add")
    public ResponseEntity<?>addMerchant(@RequestBody @Valid Merchant merchant , Errors errors){
        if(errors.hasErrors()){
            String meesage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(meesage);
        }

        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant add succfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateMerchant(@PathVariable String id ,@RequestBody @Valid Merchant merchant ,Errors errors){
        if(errors.hasErrors()){
            String meesage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(meesage);
        }
        boolean updated = merchantService.updatedmerchant(id,merchant);

        if(updated == false){
            return ResponseEntity.status(400).body("Merchant ID not found ");
        }
        return ResponseEntity.status(200).body("Updated succfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteMerchant(@PathVariable String id){
        boolean deleted = merchantService.deletemerchant(id);

        if(deleted == false){
            return ResponseEntity.status(400).body(" ID NOT Correct");
        }

        return ResponseEntity.status(200).body("deleted succfully");
    }


}
