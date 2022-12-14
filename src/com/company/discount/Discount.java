package com.company.discount;

import com.company.Cart;
import com.company.Product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class Discount {
    private UUID id;
    private String name;
    private Double thresholdAmount;
    private Set<String> applicableCategories;

    public Discount(UUID id, String name, Double thresholdAmount) {
        this.id = id;
        this.name = name;
        this.thresholdAmount = thresholdAmount;
    }

    public Discount(UUID id, String name, Double thresholdAmount, Set<String> applicableCategories) {
        this(id, name, thresholdAmount);
        this.applicableCategories = applicableCategories;
    }

    public abstract Double calculateCartAmountAfterDiscountApplied(Double amount);

    public boolean decideDiscountIsApplicableToCart(Cart cart){
        return cart.calculateCartTotalAmount() > thresholdAmount;
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

    public Double getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(Double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public Set<String> getApplicableCategories() {
        return applicableCategories;
    }

    public void setApplicableCategories(Set<String> applicableCategories) {
        this.applicableCategories = applicableCategories;
    }
}
