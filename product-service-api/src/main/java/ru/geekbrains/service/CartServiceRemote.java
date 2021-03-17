package ru.geekbrains.service;

import java.util.List;

public interface CartServiceRemote {

    void addToCart(ProductRepr product);

    List<ProductRepr> getCartList();

    void removeFromCart(ProductRepr product);
}
