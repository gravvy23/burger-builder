package com.example.burgerbuilder.model;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Column(columnDefinition = "name")
    private String name;

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

    public void setName(String newValue) {
        name = newValue;
    }

    public String getName() {
        return name;
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
}
