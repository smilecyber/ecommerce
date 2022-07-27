package com.company;


import java.util.Map;
import java.util.UUID;

public class Cart {
    private UUID customerId;
    private UUID discountId;
    private Map<Product, Integer> productMap;

    public Cart( UUID customerId) {
        this.customerId = customerId;
    }

    public Cart(UUID customerId, UUID discountId, Map<Product, Integer> productMap) {

        this(customerId);
        this.discountId = discountId;
        this.productMap = productMap;
    }

    public Double calculateCartTotalAmount() {
        double totalAmount = 0d;
        for (Product product : productMap.keySet()){
            totalAmount += product.getPrice() * productMap.get(product);
        }
        return totalAmount;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getDiscountId() {
        return discountId;
    }

    public void setDiscountId(UUID discountId) {
        this.discountId = discountId;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product, Integer> productMap) {
        this.productMap = productMap;
    }
}
