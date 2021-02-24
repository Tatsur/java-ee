package ru.geekbrains.controller;

import ru.geekbrains.persists.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class CartController implements Serializable {

    private final Map<Long, Product> productMap = new HashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public void addToCart(Product product) {
        Long key = identity.incrementAndGet();
        Product cartProduct = new Product(product);
        cartProduct.setCartKey(key);
        productMap.put(key,cartProduct);
    }

    public void removeFromCart(Product product) {
        productMap.remove(product.getCartKey());
    }
}
