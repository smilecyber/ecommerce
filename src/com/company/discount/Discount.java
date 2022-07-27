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
        Set<Product> productList = cart.getProductMap().keySet();
        double eligibleTotalProductAmount = 0d;
        for (Product product : productList){
            // multiply with quantity
            if (applicableCategories != null && applicableCategories.size() > 0){
                if (applicableCategories.contains(product.getCategoryName())){
                    eligibleTotalProductAmount += (product.getPrice()
                            * cart.getProductMap().get(product));
                }
            }else {
                return cart.calculateCartTotalAmount() > thresholdAmount;
            }
        }
        return eligibleTotalProductAmount > thresholdAmount;
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
