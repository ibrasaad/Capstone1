package com.example.webapplication.service;

import com.example.webapplication.modle.Merchant;
import com.example.webapplication.modle.MerchantStock;
import com.example.webapplication.modle.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    private final ProductService productService;
    private final MerchantService merchantService ;
    private ArrayList<MerchantStock> merchantStockslist = new ArrayList<>();

    public MerchantStockService(ProductService productService, MerchantService merchantService) {
        this.productService = productService;
        this.merchantService = merchantService;
    }

    public ArrayList<MerchantStock>getMerchantStockslist(){
            return merchantStockslist;
        }

    public boolean addMerchantStock(MerchantStock merchantStock){
        boolean productidmatched=false;
         boolean merchmatched=false;
        for(Product p : productService.getProductslist()){
            if(p.getId().equalsIgnoreCase(merchantStock.getProuductid())){
                productidmatched=true;
                break;
            }

            }
        for(Merchant m : merchantService.getMerchantslist()){
            if(m.getId().equalsIgnoreCase(merchantStock.getMerchantid())){
                merchmatched=true;
                break;
            }
        }
        if(productidmatched && merchmatched) {
            merchantStock.setStock(11);
            merchantStockslist.add(merchantStock);
            return true;
        }
        return false ;
    }

    public boolean updateMerchant(String id , MerchantStock merchantStock){
        boolean idFound = false;
        boolean productidmatched=false;
        boolean merchmatched=false;
       for(MerchantStock ms : merchantStockslist){
           if (ms.getId().equalsIgnoreCase(id)){
               idFound=true;
           }

       }
        for(Product p : productService.getProductslist()){
            if(p.getId().equalsIgnoreCase(merchantStock.getProuductid())){
                productidmatched=true;
                break;
            }

        }
        for(Merchant m : merchantService.getMerchantslist()){
            if(m.getId().equalsIgnoreCase(merchantStock.getMerchantid())){
                merchmatched=true;
                break;
            }
        }
        if(productidmatched && merchmatched && idFound) {
            merchantStock.setStock(11);
            merchantStockslist.add(merchantStock);
            return true;
        }
        return false ;
    }

    public boolean deleteMerchant(String id){
        for(MerchantStock ms : merchantStockslist){
            if(ms.getId().equalsIgnoreCase(id)){
                merchantStockslist.remove(ms);
                return true ;
            }
        }
        return false ;

    }

    public boolean updateStockbyMerchant(String merchantid , int newstock){
        for(MerchantStock ms : merchantStockslist){
            if(ms.getMerchantid().equalsIgnoreCase(merchantid)){
                ms.setStock(newstock);
                return true;
            }
        }
        return false;

    }

    public boolean applyDiscountForProduct(String merchantId, String productId) {

        MerchantStock target = null;

        for (MerchantStock ms : merchantStockslist) {
            if (ms.getMerchantid().equalsIgnoreCase(merchantId)
                    && ms.getProuductid().equalsIgnoreCase(productId)) {
                target = ms;
                break;
            }
        }

        if (target == null) {
            return false;
        }

        Product product = null;
        for (Product p : productService.getProductslist()) {
            if (p.getId().equalsIgnoreCase(productId)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            return false;
        }

        int stock = target.getStock();
        double price = product.getPrice();
        double discount = 0;

        if (stock >= 50) {
            discount = 0.20;
        } else if (stock >= 20) {
            discount = 0.10;
        } else {
            discount = 0;
        }

        double finalPrice = price - (price * discount);
        product.setPrice(finalPrice);

        return true;
    }













}
