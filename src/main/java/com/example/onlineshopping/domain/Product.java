package com.example.onlineshopping.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    public Product(String name, String description, float price, Seller seller, Set<Order> orders, Set<Review> reviews) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.orders = orders;
        this.reviews = reviews;
    }

    @ManyToOne
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Review> reviews;

   }
