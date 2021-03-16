package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addToCart(ProductRepr product);

    List<ProductRepr> getCartList();

    void removeFromCart(ProductRepr product);
}
