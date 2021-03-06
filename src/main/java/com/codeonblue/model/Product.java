package com.codeonblue.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Product {

    public Product() {}

    public Product(String name) {
        this.name = name;
    }

    public Product(Long id,
                   String name,
                   String category,
                   String description,
                   double price,
                   String condition,
                   String status,
                   int unitStock,
                   String manufacturer) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.status = status;
        this.unitStock = unitStock;
        this.manufacturer = manufacturer;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;
    private String condition;
    private String status;
    private int unitStock;
    private String manufacturer;

    @Transient
    private MultipartFile image;

    private String imageFileName;


    public Product(String name,
                   String category,
                   String description,
                   double price,
                   String condition,
                   String status,
                   int unitStock,
                   String manufacturer) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.status = status;
        this.unitStock = unitStock;
        this.manufacturer = manufacturer;
    }

    public Product(Long id,
                   String name,
                   String category,
                   String description,
                   double price,
                   String condition,
                   String status,
                   int unitStock,
                   String manufacturer,
                   MultipartFile image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.status = status;
        this.unitStock = unitStock;
        this.manufacturer = manufacturer;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUnitStock() {
        return unitStock;
    }

    public void setUnitStock(int unitStock) {
        this.unitStock = unitStock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                ", status='" + status + '\'' +
                ", unitStock=" + unitStock +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
