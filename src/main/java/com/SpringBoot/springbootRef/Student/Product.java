package com.SpringBoot.springbootRef.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private Double price;
}
