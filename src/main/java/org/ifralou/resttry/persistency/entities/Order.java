package org.ifralou.resttry.persistency.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class Order {
    private int orderId;
    private LocalDate orderDate;

    private List<Item> items = new ArrayList<>();

    private int customerId;

    @Column(name = "customer_id", insertable = false, updatable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "order_date")
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate order_date) {
        this.orderDate = order_date;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", order_date=" + orderDate +
                '}';
    }
}
