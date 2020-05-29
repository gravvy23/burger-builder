package com.example.burgerbuilder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders_ingredients")
public class OrderIngredient {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    
    public void setOrder(Order order) {
    	this.order = order;
    }
 
    public Order getOrder() {
    	return this.order;
    }
    
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    public void setIngredient(Ingredient ingredient) {
    	this.ingredient = ingredient;
    }
    
    public Ingredient getIngredient() {
    	return this.ingredient;
    }
 
    @Column(columnDefinition = "position")
    private Integer position;
    
    public void setPosition(Integer position) {
    	this.position = position;
    }

    public Integer getPosition() {
    	return this.position;
    }

    Long uid;
    
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return this.uid;
    }
}
