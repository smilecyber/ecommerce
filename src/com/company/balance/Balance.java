package com.company.balance;

import java.util.UUID;

public abstract class Balance {
    private Double balance;
    private UUID customerId;

    public Balance(Double balance, UUID customerId) {
        this.balance = balance;
        this.customerId = customerId;
    }

    public abstract Double addBalance(Double additionalAmount);

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
