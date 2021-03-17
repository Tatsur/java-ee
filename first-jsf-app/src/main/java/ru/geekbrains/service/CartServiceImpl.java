package ru.geekbrains.service;


import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Stateful
@Remote(CartServiceRemote.class)
public class CartServiceImpl implements CartService, CartServiceRemote {

    private final Map<Long, ProductRepr> productMap = new HashMap<>();
    private final AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public void addToCart(ProductRepr product) {
        productMap.put(product.getId(),product);
    }

    @Override
    public List<ProductRepr> getCartList() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void removeFromCart(ProductRepr product) {
        productMap.remove(product.getId());
    }
}
