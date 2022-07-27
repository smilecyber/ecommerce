package com.company.balance;

import java.util.UUID;

public class GiftCardBalance extends Balance{

    public GiftCardBalance(Double balance, UUID customerId) {
        super(balance, customerId);
    }
    @Override
    public Double addBalance(Double additionalAmount) {
        double promotionAmount = additionalAmount* 10 / 100;
        setBalance(getBalance() + additionalAmount + promotionAmount);
        return getBalance();
    }
}
