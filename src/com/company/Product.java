package com.company;

import com.company.category.Category;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.company.StaticConstants.CATEGORY_LIST;

public class Product {
    private UUID id;
    private String name;
    private Double cost;
    private Double price;
    private Integer stock;
    private Integer remainingStock;
    private UUID categoryId;

    public Product(UUID id, String name, Double cost, Double price, Integer stock, Integer remainingStock, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.stock = stock;
        this.remainingStock = remainingStock;
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(Integer remainingStock) {
        this.remainingStock = remainingStock;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() throws Exception {
        for (Category category : CATEGORY_LIST){
            if (getCategoryId().toString().equals(category.getId().toString())){
                return category.getName();
            }
        }
        throw new Exception("Category not found, " + getId());
    }

    public LocalDateTime getDeliveryDueDate() {
        for (Category category : CATEGORY_LIST){
            if (getCategoryId().toString().equals(category.getId().toString())){
                return category.findDeliveryDueDate();
            }
        }
        throw new Exception("")
    }

    public String getCategoryCode() {
        for (Category category : CATEGORY_LIST){
            if (getCategoryId().toString().equals(category.getId().toString())){
                return category.generateCategoryCode();
            }
        }
        // should we show the students ?
        //throw new Exception("Category not found");
        return null;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
