package com.codeonblue.builder;

import com.codeonblue.model.Product;

public class ProductBuilder {

    private Long id;
    private String name;
    private String category;
    private String description;
    private double price;
    private String condition;
    private String status;
    private int unitStock;
    private String manufacturer;

    public ProductBuilder(){ }

    public ProductBuilder id(Long id){
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name){
        this.name = name;
        return this;
    }

    public ProductBuilder category(String category){
        this.category = category;
        return this;
    }

    public ProductBuilder description(String description){
        this.description = description;
        return this;
    }

    public ProductBuilder price(double price){
        this.price = price;
        return this;
    }

    public ProductBuilder condition(String condition){
        this.condition = condition;
        return this;
    }

    public ProductBuilder status(String status){
        this.status = status;
        return this;
    }

    public ProductBuilder unitStock(int unitStock){
        this.unitStock = unitStock;
        return this;
    }

    public ProductBuilder manufacturer(String manufacturer){
        this.manufacturer = manufacturer;
        return this;
    }

    public Product createProductWithId() {
        return new Product(id, name, category, description, price, condition, status, unitStock, manufacturer );
    }

    public Product createProductWithoutId() {
        return new Product(name, category, description, price, condition, status, unitStock, manufacturer);
    }
}
