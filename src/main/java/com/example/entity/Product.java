package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private int quantity;
    private String description;

    public Product() {}

    public Product(String name,double price,int quantity,String description){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.description=description;
    }

    public int getId(){ return id; }

    public String getName(){ return name; }

    public double getPrice(){ return price; }

    public int getQuantity(){ return quantity; }

    public String getDescription(){ return description; }
}