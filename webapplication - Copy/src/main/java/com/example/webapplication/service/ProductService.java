package com.example.webapplication.service;

import com.example.webapplication.modle.Category;
import com.example.webapplication.modle.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final CategoryService categoryService;
    private ArrayList<Product> productslist = new ArrayList<>();
    ArrayList<Product> lowbudget = new ArrayList<>();
    ArrayList<String> views = new ArrayList<>();


    public ProductService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ArrayList<Product> getProductslist() {
        return productslist;
    }

    public boolean addpouduct(Product product) {
        boolean samecategoryid = false;
        for (Category c : categoryService.categorieslist) {
            if (c.getId().equalsIgnoreCase(product.getCategoryid())) {
                samecategoryid = true;
                break;
            }
        }
        if (samecategoryid) {
            productslist.add(product);
            return true;
        }
        return false;
    }

    public boolean deleteProuduct(String id) {
        for (Product p : productslist) {
            if (p.getId().equalsIgnoreCase(id)) {
                productslist.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean updateProuduct(String id, Product product) {
        for (int i = 0; i < productslist.size(); i++) {
            if (productslist.get(i).getId().equalsIgnoreCase(id)) {
                for (Category c : categoryService.categorieslist) {
                    if (c.getId().equalsIgnoreCase(product.getCategoryid())) {
                        productslist.set(i, product);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Product> productTier(String tier) {

        ArrayList<Product> low = new ArrayList<>();
        ArrayList<Product> mid = new ArrayList<>();
        ArrayList<Product> high = new ArrayList<>();

        for (Product p : productslist) {
            if (p.getPrice() <= 50) {
                low.add(p);
            } else if (p.getPrice() <= 200) {
                mid.add(p);
            } else {
                high.add(p);
            }
        }

        switch (tier.toLowerCase()) {
            case "low":
                return low;
            case "mid":
                return mid;
            case "high":
                return high;
        }

        return null;
    }

    public ArrayList<Product> getProductsinRange(double maxPrice) {

        ArrayList<Product> rangelist = new ArrayList<>();

        for (Product p : productslist) {
            if (p.getPrice() <= maxPrice) {
                rangelist.add(p);
            }
        }

        return rangelist;
    }

    public String bestSuggestedPrice(String categoryId) {

        double total = 0;
        int count = 0;

        for (Product p : productslist) {

            if (p.getCategoryid().equalsIgnoreCase(categoryId)) {
                total += p.getPrice();
                count++;
            }
        }

        if (count == 0) {
            return "No products found in this category";
        }

        double avg = total / count;
        double sugested = avg * 0.95;

        return "best suggested price: " + sugested;
    }


    public ArrayList<Product> searchProductsByKeyword(String keyword) {

        ArrayList<Product> result = new ArrayList<>();

        String key = keyword.toLowerCase();

        for (Product p : productslist) {
            if (p.getName().toLowerCase().contains(key)) {
                result.add(p);
            }
        }

        return result;
    }






}
