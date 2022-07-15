package org.ifralou.resttry.persistency.entities;

import javax.persistence.*;

@Entity
@Table(name = "email_address")
public class EmailAddress {
    private int id;
    private String address;

    private int customerId;

    @Column(name = "customer_id", insertable = false, updatable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String email) {
        this.address = email;
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
