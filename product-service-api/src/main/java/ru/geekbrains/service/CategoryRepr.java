package ru.geekbrains.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepr implements Serializable {

    private Long id;

    private String name;

    private String description;

    private List<ProductRepr> products = new ArrayList<>();

    private Long parent;

    public CategoryRepr(Long id, String name, String description, Long parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    public CategoryRepr() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public List<ProductRepr> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRepr> products) {
        this.products = products;
    }
}
