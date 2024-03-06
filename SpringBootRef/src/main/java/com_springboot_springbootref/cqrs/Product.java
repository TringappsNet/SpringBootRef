package com_springboot_springbootref.cqrs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Declaring id field as primary key
    private String name; // Declaring name field
    private Double price; // Declaring price field

    // Getter method for id field
    public Long getId() {
        return id;
    }

    // Setter method for id field
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method for name field
    public String getName() {
        return name;
    }

    // Setter method for name field
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for price field
    public Double getPrice() {
        return price;
    }

    // Setter method for price field
    public void setPrice(Double price) {
        this.price = price;
    }
}
