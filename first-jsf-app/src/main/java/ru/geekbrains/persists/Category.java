package ru.geekbrains.persists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "categories")
@NamedQueries({
        @NamedQuery(name="findAllCategories",query = "from Category "),
        @NamedQuery(name = "countAllCategories",query = "select count(*) from Category "),
        @NamedQuery(name = "deleteCategoryById",query = "delete from Category c where c.id = :id" )
        ,@NamedQuery(name = "findCategoryById",query = "from Category c where c.id=:id")

})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private List<Product> products = new ArrayList<>();

    @Column
    private Long parent;

    public Category(Long id, String name, String description, Long parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    public Category() {

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
