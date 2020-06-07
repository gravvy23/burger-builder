package com.example.burgerbuilder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model representing one ingredient
 */
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

    /**
     * ingredient name setter
     * @param newValue
     */
    public void setName(String newValue) {
        name = newValue;
    }

    /**
     * ingredient name getter
     * @return unique ingredient name
     */
    public String getName() {
        return name;
    }

    /**
     * ingredient display name setter
     * @param newValue
     */
    public void setDisplayname(String newValue) {
        displayname = newValue;
    }

    /**
     * ingredient display name getter
     * @return ingredient display name
     */
    public String getDisplayname() {
        return displayname;
    }

    /**
     * ingredient price setter
     * @param newValue
     */
    public void setPrice(Double newValue) {
        price = newValue;
    }

    /**
     * ingredient price getter
     * @return ingredient price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * unique id setter
     * @param newValue
     */
    public void setId(Long newValue) {
        id = newValue;
    }

    /**
     * unique id getter
     * @return unique id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * set all orders in which ingredient is used
     * @param newValue
     */
    public void setOrders(List<OrderIngredient> newValue) {
    	this.orders = newValue;
    }

    /**
     * get all orders in which ingredient is used
     * @return all orders in which ingredient is used
     */
    public List<OrderIngredient> getOrders() {
    	return this.orders;
    }
}
