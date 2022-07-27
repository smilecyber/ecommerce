package com.company.balance;

import java.util.UUID;

public class CustomerBalance extends Balance {

    public CustomerBalance(Double balance, UUID customerId) {
        super(balance, customerId);
    }

    @Override
    public Double addBalance(Double additionalAmount) {
        setBalance(getBalance() + additionalAmount);
        return getBalance();
    }
}
