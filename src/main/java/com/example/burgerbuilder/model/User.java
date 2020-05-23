package com.example.burgerbuilder.model;

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

    @Id
    private Long id;

    public void setName(String newValue) {
        name = newValue;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String newValue) {
        surname = newValue;
    }

    public String getSurname() {
        return surname;
    }

    public void setMail(String newValue) {
        mail = newValue;
    }

    public String getMail() {
        return mail;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public Long getId() {
        return id;
    }
}