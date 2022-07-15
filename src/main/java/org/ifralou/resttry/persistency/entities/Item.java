package org.ifralou.resttry.persistency.entities;

import org.ifralou.resttry.persistency.repositories.ProductRepository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Item {
    private int id;
    private int quantity;
    private BigDecimal total;

    private Product product;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", total=" + total +
                ", product=" + product +
                '}';
    }
}
