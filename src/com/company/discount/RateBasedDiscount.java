package com.company.discount;

import com.company.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class RateBasedDiscount extends Discount{
    private Double discountRate;

    public RateBasedDiscount(UUID id, String name, Double thresholdAmount) {
        super(id, name, thresholdAmount);
    }

    public RateBasedDiscount(UUID id, String name, Double thresholdAmount, Set<String> applicableCategories, Double discountRate) {
        super(id, name, thresholdAmount, applicableCategories);
        this.discountRate = discountRate;
    }


    @Override
    public Double calculateCartAmountAfterDiscountApplied(Double amount) {
        return amount - (amount * getDiscountRate() / 100);
    }

    public Double calculateCartAmountAfterDiscountApplied(Double amount, Map<Product, Integer> productMap) {
        Set<Product> productList = productMap.keySet();
        double eligibleTotalProductAmount = 0d;
        for (Product product : productList){
            // multiply with quantity
            if (getApplicableCategories().size() > 0){
                if (getApplicableCategories().contains(product.getCategoryName())){
                    eligibleTotalProductAmount += (product.getPrice()
                            * productMap.get(product));
                }
            }
        }
        return amount - (eligibleTotalProductAmount * getDiscountRate() / 100);
    }


    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }
}
