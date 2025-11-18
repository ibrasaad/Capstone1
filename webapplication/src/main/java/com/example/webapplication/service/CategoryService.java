package com.example.webapplication.service;

import com.example.webapplication.modle.Category;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
     ArrayList<Category> categorieslist = new ArrayList<>();

    public ArrayList<Category> getCategorieslist(){
    return categorieslist ;
    }

    public void addcatgory(Category category){
        categorieslist.add(category);
    }

    public boolean updatecatgory(String id , Category category){
        for(int i=0; i< categorieslist.size(); i++){
            if(categorieslist.get(i).getId().equalsIgnoreCase(id)){
                categorieslist.set(i,category);
                return true;
            }
        }
        return false;


    }

    public boolean deletecatgory(String id){
       for(Category c : categorieslist){
           if(c.getId().equalsIgnoreCase(id)){
               categorieslist.remove(c);
               return true;
           }
       }
       return false ;
    }






}
