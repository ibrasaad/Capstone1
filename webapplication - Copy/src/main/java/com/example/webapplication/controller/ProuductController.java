package com.example.webapplication.controller;

import com.example.webapplication.modle.Product;
import com.example.webapplication.modle.User;
import com.example.webapplication.service.CategoryService;
import com.example.webapplication.service.MerchantStockService;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
    public class ProuductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity<?>getallprouduct(){
        return ResponseEntity.status(200).body(productService.getProductslist());
    }
    @PostMapping("/add")
    public ResponseEntity<?>addProuduct(@RequestBody @Valid Product product ,Errors errors ){

        if(errors.hasErrors()){
            String meesage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(meesage);
        }
        boolean addproduct = productService.addpouduct(product);

        if(addproduct == false){
            return ResponseEntity.status(400).body("there is no catgory id matched");
        }

        return ResponseEntity.status(200).body("add succfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProuduct(@PathVariable String id , @RequestBody @Valid Product product){
        boolean updated = productService.updateProuduct(id, product);

        if(updated == false){
            return  ResponseEntity.status(400).body("please enter valid informations & make sure catgory id matched");
        }
        return ResponseEntity.status(200).body("updated succfully");
    }
@DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteProuduct(@PathVariable String id ){
        boolean delated = productService.deleteProuduct(id);

        if(delated == false){
            return ResponseEntity.status(400).body("ID Not found");
        }
        return ResponseEntity.status(200).body("Deleted Succfully");


    }
    @GetMapping("/tier/{tier}")
    public ResponseEntity<?> getProductsByTier(@PathVariable String tier) {

        ArrayList<Product> products = productService.productTier(tier);

        if (products == null) {
            return ResponseEntity.status(400).body("invalid tier");
        }

        if (products.isEmpty()) {
            return ResponseEntity.status(200).body("no products in this tier");
        }

        return ResponseEntity.status(200).body(products);
    }



    @GetMapping("/bestprice/{categoryId}")
    public ResponseEntity<?> getBestSuggestedPrice(@PathVariable String categoryId) {

        String meesage = productService.bestSuggestedPrice(categoryId);

        return ResponseEntity.status(200).body(meesage);
    }


    @GetMapping("/search-keyword/{keyword}")
    public ResponseEntity<?> searchByKeyword(@PathVariable String keyword) {

        ArrayList<Product> list = productService.searchProductsByKeyword(keyword);

        return ResponseEntity.status(200).body(list);
    }
























}
