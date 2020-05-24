package com.example.burgerbuilder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "surname")
    private String surname;

    @Column(columnDefinition = "mail")
    private String mail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Order> orders = new ArrayList<Order>();
    
    @Id
    private String id;

    public void setName(String newValue) {
        this.name = newValue;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String newValue) {
        this.surname = newValue;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setMail(String newValue) {
        this.mail = newValue;
    }

    public String getMail() {
        return this.mail;
    }
    
    public void setOrders(List<Order> newValue) {
    	this.orders = newValue;
    }
    
    public List<Order> getOrders() {
    	return this.orders;
    }

    public void setId(String newValue) {
        this.id = newValue;
    }

    public String getId() {
        return this.id;
    }
    
    public void addOrder(Order order) {
    	this.orders.add(order);
    	order.setUser(this);
    }
}