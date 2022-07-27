package com.company.discount;

import java.util.Set;
import java.util.UUID;

public class AmountBasedDiscount extends Discount{
    private Double discountAmount;

    public AmountBasedDiscount(UUID id, String name, Double thresholdAmount, Double discountAmount) {
        super(id, name, thresholdAmount);
        this.discountAmount = discountAmount;
    }

    public AmountBasedDiscount(UUID id, String name, Double thresholdAmount, Set<String> applicableCategories, Double discountAmount) {
        super(id, name, thresholdAmount, applicableCategories);
        this.discountAmount = discountAmount;
    }

    @Override
    public Double calculateCartAmountAfterDiscountApplied(Double amount) {
        double finalAmount = amount - discountAmount;
        if (finalAmount > 0){
            return finalAmount;
        }else {
            return 0d;
        }
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
