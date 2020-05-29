package com.example.burgerbuilder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "displayname")
    private String displayname;

    @Column(columnDefinition = "price")
    private Double price;

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )
    private Long id;
    
//    @ManyToMany(mappedBy = "ingredients")
//    @JsonIgnore
//    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "ingredient")
    @JsonIgnore
    private List<OrderIngredient> orders = new ArrayList<>();

    public void setName(String newValue) {
        name = newValue;
    }

    public String getName() {
        return name;
    }

    public void setDisplayname(String newValue) {
        displayname = newValue;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setPrice(Double newValue) {
        price = newValue;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public Long getId() {
        return id;
    }
    
    public void setOrders(List<OrderIngredient> newValue) {
    	this.orders = newValue;
    }
    
    public List<OrderIngredient> getOrders() {
    	return this.orders;
    }
}
