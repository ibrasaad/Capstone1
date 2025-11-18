package com.example.webapplication.controller;

import com.example.webapplication.modle.Category;
import com.example.webapplication.modle.User;
import com.example.webapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity<?> getallusers(){
        return ResponseEntity.status(200).body(userService.getUserslist());
    }
    @PostMapping("/add")
    public ResponseEntity<?>addusers(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("add succfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id , @RequestBody @Valid User user ){
        boolean updated = userService.updateuser(id,user);
        if(updated == false){
            return ResponseEntity.status(400).body("ID Not Found ");
        }
        return ResponseEntity.status(200).body("updated succfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id ){
        boolean deleted = userService.deleteuser(id);
        if(deleted == false){
            return ResponseEntity.status(400).body("ID Not found ");
        }
        return ResponseEntity.status(200).body("deleted succfully");
    }

    @PostMapping("/buyProuductDirectly/{userId}/{productId}/{merchantId}")
    public ResponseEntity<?> buyProductDirectly(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId) {

        int result = userService.buyprouductdirctly(userId, productId, merchantId);

        switch (result) {
            case 0:
                return ResponseEntity.status(200).body("Purchase succfully");
            case 1:
                return ResponseEntity.status(400).body("User not found");
            case 2:
                 return ResponseEntity.status(400).body("Product not found");
            case 3:
                 return ResponseEntity.status(400).body("Merchant does not have this product");
            case 4:
                 return ResponseEntity.status(400).body("User balance is not enough");
            case 5:
                return ResponseEntity.status(400).body("Product out of stock");
            default:
                return ResponseEntity.status(400).body("null");
        }
    }


    @GetMapping("/roles/summary")
    public ResponseEntity<?> getRolesSummary() {
        String result = userService.rolesSummary();
        return ResponseEntity.status(200).body(result);
    }


}
