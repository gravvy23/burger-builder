package com.example.burgerbuilder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Model for user
 */
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

    /**
     * set user name
     * @param newValue
     */
    public void setName(String newValue) {
        this.name = newValue;
    }

    /**
     * get user name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * set user surname
     * @param newValue
     */
    public void setSurname(String newValue) {
        this.surname = newValue;
    }

    /**
     * get user surname
     * @return
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * set user mail
     * @param newValue
     */
    public void setMail(String newValue) {
        this.mail = newValue;
    }

    /**
     * get user mail
     * @return
     */
    public String getMail() {
        return this.mail;
    }
    
    /**
     * set user orders
     * @param newValue
     */
    public void setOrders(List<Order> newValue) {
    	this.orders = newValue;
    }
    
    /**
     * get user orders
     * @return
     */
    public List<Order> getOrders() {
    	return this.orders;
    }

    /**
     * set user unique id
     * @param newValue
     */
    public void setId(String newValue) {
        this.id = newValue;
    }

    /**
     * get user unique id
     * @return
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * add new order 
     * @param order new order
     */
    public void addOrder(Order order) {
    	this.orders.add(order);
    	order.setUser(this);
    }
}