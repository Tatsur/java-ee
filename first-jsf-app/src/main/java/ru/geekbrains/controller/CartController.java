package ru.geekbrains.controller;

import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    CartService cartService;

    public List<ProductRepr> getAllProducts() {
        return cartService.getCartList();
    }

    public void addToCart(ProductRepr product) {
        cartService.addToCart(product);
    }

    public void removeFromCart(ProductRepr product) {
        cartService.removeFromCart(product);
    }
}
