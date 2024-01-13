package com.codegym.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    private boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId() == product.getId()) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId() == product.getId()) {
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(), newQuantity);
        }
    }

    public Integer countProductQuantity() {
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity() {
        return products.size();
    }

    public Float countTotalPayment() {
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += (float) (entry.getKey().getPrice() * (float) entry.getValue());
        }
        return payment;
    }

    public void removeCartItem(Product product) {
        if (checkItemInCart(product)) {
            Map.Entry<Product, Integer> entry = selectItemInCart(product);
            if (entry.getValue() == 1) {
                products.remove(entry.getKey());
            } else {
                Integer newQuantity = entry.getValue() - 1;
                products.replace(entry.getKey(), newQuantity);
            }
        }
    }

    public void updateCartItem(Product product, Integer quantity) {
        if (checkItemInCart(product)) {
            Map.Entry<Product, Integer> entry = selectItemInCart(product);
            if (quantity == 0) {
                products.remove(entry.getKey());
            } else {
                products.replace(entry.getKey(), quantity);
            }
        }
    }

    public void deleteCartItem(Product product) {
        if (checkItemInCart(product)) {
            Map.Entry<Product, Integer> entry = selectItemInCart(product);
            products.remove(entry.getKey());
        }
    }
}
