package com.company.checkout;

import com.company.Customer;
import com.company.balance.Balance;
import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;

import java.util.UUID;

import static com.company.StaticConstants.CUSTOMER_BALANCE_LIST;
import static com.company.StaticConstants.GIFT_CARD_BALANCE_LIST;

public class CustomerBalanceCheckoutServiceImpl implements CheckoutService{
    @Override
    public boolean checkout(Customer customer, Double totalAmount) {
        CustomerBalance customerBalance = findCustomerBalance(customer.getId());
        if (customerBalance != null){
            double finalBalance = customerBalance.getBalance() - totalAmount;
            if (finalBalance > 0){
                customerBalance.setBalance(finalBalance);
                return true;
            }
        }
        return false;
    }

    private CustomerBalance findCustomerBalance(UUID customerId){
        for (CustomerBalance customerBalance : CUSTOMER_BALANCE_LIST){
            if (customerId.toString().equals(customerBalance.getCustomerId().toString())){
                return customerBalance;
            }
        }
        CustomerBalance customerBalance = new CustomerBalance(0d, customerId);
        CUSTOMER_BALANCE_LIST.add(customerBalance);
        return customerBalance;
    }
}
