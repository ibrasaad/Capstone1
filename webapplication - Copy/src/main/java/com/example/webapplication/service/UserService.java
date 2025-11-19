package com.example.webapplication.service;

import com.example.webapplication.modle.MerchantStock;
import com.example.webapplication.modle.Product;
import com.example.webapplication.modle.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;
    private final ProductService productService;

    ArrayList<User> userslist = new ArrayList<>();
    ArrayList<User> buyprouductdirctlylist = new ArrayList<>();

    public UserService(MerchantStockService merchantStockService,
                       MerchantService merchantService,
                       ProductService productService) {
        this.merchantStockService = merchantStockService;
        this.merchantService = merchantService;
        this.productService = productService;
    }

    public ArrayList<User> getUserslist() {
        return userslist;
    }

    public void addUser(User user) {
        userslist.add(user);
    }

    public boolean updateuser(String id, User user) {
        for (int i = 0; i < userslist.size(); i++) {
            if (userslist.get(i).getId().equalsIgnoreCase(id)) {
                userslist.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteuser(String id) {
        for (int i = 0; i < userslist.size(); i++) {
            if (userslist.get(i).getId().equalsIgnoreCase(id)) {
                userslist.remove(i);
                return true;
            }
        }
        return false;
    }

    public int buyprouductdirctly(String userId, String productId, String merchantId) {

        User user = null;
        for (User u : userslist) {
            if (u.getId().equalsIgnoreCase(userId)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            return 1;
        }

        Product product = null;
        for (Product p : productService.getProductslist()) {
            if (p.getId().equalsIgnoreCase(productId)) {
                product = p;
                break;
            }
        }
        if (product == null) {
            return 2;
        }

        MerchantStock merchantStock = null;
        for (MerchantStock ms : merchantStockService.getMerchantStockslist()) {
            if (ms.getMerchantid().equalsIgnoreCase(merchantId)
                    && ms.getProuductid().equalsIgnoreCase(productId)) {
                merchantStock = ms;
                break;
            }
        }
        if (merchantStock == null) {
            return 3;
        }

        if (merchantStock.getStock() <= 0) {
            return 5;
        }

        if (user.getBalance() < product.getPrice()) {
            return 4;
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());

        return 0;
    }

    public User getUserById(String userid) {
        for (User us : userslist) {
            if (us.getId().equalsIgnoreCase(userid)) {
                return us;
            }
        }
        return null;
    }

    public String rolesSummary() {

        int admins = 0;
        int customers = 0;

        for (User u : userslist) {
            if (u.getRole().equalsIgnoreCase("Admin")) {
                admins++;
            } else if (u.getRole().equalsIgnoreCase("Customer")) {
                customers++;
            }
        }

        return "Admins: " + admins + "\nCustomers: " + customers;
    }





}
