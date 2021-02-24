package ru.geekbrains.controller;

import ru.geekbrains.persists.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Long, Product> productMap = new HashMap<>();

    public void addToCart(Product product) {
    }

    public String removeFromCart(Product product) {
        return "";
    }
}
