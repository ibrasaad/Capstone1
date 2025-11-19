package com.example.webapplication.controller;

import com.example.webapplication.modle.MerchantStock;
import com.example.webapplication.modle.Product;
import com.example.webapplication.service.MerchantStockService;
import com.example.webapplication.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity<?>getAllMerchantStock(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStockslist());
    }
    @PostMapping("/add")
    public ResponseEntity<?>addMerchtoStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String meesage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(meesage);
        }
        boolean added = merchantStockService.addMerchantStock(merchantStock);
        if(added == false){
            return ResponseEntity.status(400).body("Merchat ID or Prouduct ID Doesnt Match ");
        }
        return ResponseEntity.status(200).body("Merch add succfully to the stock");
    }
@PutMapping("/update/{id}")
    public ResponseEntity<?>updateMerchinStock(@PathVariable String id ,@RequestBody @Valid MerchantStock merchantStock , Errors errors){
        boolean updated = merchantStockService.updateMerchant(id, merchantStock);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(updated == false){
            return ResponseEntity.status(400).body("ID Not Found OR Product ID not Matched or MerchID Not Matched");
        }
        merchantStockService.updateMerchant(id, merchantStock);
        return ResponseEntity.status(200).body("updated succfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteMechinStock(@PathVariable String id ){

        boolean deleted = merchantStockService.deleteMerchant(id);

        if(deleted == false){
            return ResponseEntity.status(400).body("ID Cannot Found");
        }
        merchantStockService.deleteMerchant(id);
        return ResponseEntity.status(200).body("Deleted Succfully");

    }

    @PutMapping("/stockupdate/{merchantid}/{newstock}")
    public ResponseEntity<?>updateStockbymerchant(@PathVariable String merchantid , @PathVariable int newstock ){

        boolean updated = merchantStockService.updateStockbyMerchant(merchantid,newstock);

        if(updated == false){
            return ResponseEntity.status(400).body("ID Cannot Found or merchant ID Dosent match");
        }
        return ResponseEntity.status(200).body("Updated Succfully");

    }



    @PutMapping("/discount/{merchantId}/{productId}")
    public ResponseEntity<?> applyDiscount(@PathVariable String merchantId, @PathVariable String productId) {

        boolean applied = merchantStockService.applyDiscountForProduct(merchantId, productId);

        if (!applied) {
            return ResponseEntity.status(400).body("merchant or product not found");
        }

        return ResponseEntity.status(200).body("discount applied successfully");
    }


    @GetMapping("/low-stock")
    public ResponseEntity<?> getLowStockProducts() {

        ArrayList<MerchantStock> list = merchantStockService.getAlmostSoldOut();

        if (list.isEmpty()) {
            return ResponseEntity.status(200).body("All products have good stock");
        }

        return ResponseEntity.status(200).body(list);
    }










}
