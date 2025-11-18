package com.example.webapplication.service;

import com.example.webapplication.modle.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    private ArrayList<Merchant> merchantslist = new ArrayList<>();


    public ArrayList<Merchant> getMerchantslist(){
        return merchantslist;
    }

    public void addMerchant(Merchant merchant){
        merchantslist.add(merchant);
    }

    public boolean updatedmerchant(String id , Merchant merchant){
        for(int i =0; i<merchantslist.size(); i++){
            if(merchantslist.get(i).getId().equalsIgnoreCase(id)){
                merchantslist.set(i,merchant);
                return true ;
            }
        }
        return false ;
    }

    public boolean deletemerchant(String id ){
        for(Merchant m : merchantslist){
            if(m.getId().equalsIgnoreCase(id)){
                merchantslist.remove(m);
                return true;
            }
        }
        return false ;
    }





}
