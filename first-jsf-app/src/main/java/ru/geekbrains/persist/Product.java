package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepr;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAll",query = "from Product "),
        @NamedQuery(name = "countAll",query = "select count(*) from Product "),
        @NamedQuery(name = "deleteById",query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "findById",query = "from Product p where p.id = :id"),
        @NamedQuery(name = "findByCategoryId",query = "from Product p where p.category.id = :id"),
        @NamedQuery(name = "findByName",query = "from Product p where p.name = :name"),
})
public class Product implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @ManyToOne
//    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private BigDecimal price;

    @Column
    private Integer quantity;


    public Long getCartKey() {
        return cartKey;
    }

    public void setCartKey(Long cartKey) {
        this.cartKey = cartKey;
    }

    private Long cartKey;

    public Product() {
    }

    public Product(Product product) {
        this(product.id,product.name,product.description,product.category,product.price);
    }

    public Product(ProductRepr productRepr, Category category){
        this(productRepr.getId(),productRepr.getName(),productRepr.getDescription(),productRepr.getPrice());
        this.category = category;
    }
    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(Long id, String name, String description, Category category,BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;

    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
