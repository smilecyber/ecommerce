package com.company.checkout;

import com.company.Customer;
import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;

import java.util.UUID;

import static com.company.StaticConstants.CUSTOMER_BALANCE_LIST;
import static com.company.StaticConstants.GIFT_CARD_BALANCE_LIST;

public class MixPaymentCheckoutServiceImpl implements CheckoutService{
    @Override
    public boolean checkout(Customer customer, Double totalAmount) {
        try {
            GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());
            final double giftBalance = giftCardBalance.getBalance() - totalAmount;
            if (giftBalance > 0 ){
                giftCardBalance.setBalance(giftBalance);
            }else {
                CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                final double mixBalance = giftCardBalance.getBalance() + customerBalance.getBalance() - totalAmount;
                if (mixBalance > 0){
                    giftCardBalance.setBalance(0d);
                    customerBalance.setBalance(mixBalance);

                    return true;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
    private GiftCardBalance findGiftCardBalance(UUID customerId) throws Exception {
        for (GiftCardBalance giftCardBalance : GIFT_CARD_BALANCE_LIST){
            if (customerId.toString().equals(giftCardBalance.getCustomerId().toString())){
                return giftCardBalance;
            }
        }
        throw new Exception("Gift card balance couldn't found");
    }

    private CustomerBalance findCustomerBalance(UUID customerId) throws Exception {
        for (CustomerBalance customerBalance : CUSTOMER_BALANCE_LIST){
            if (customerId.toString().equals(customerBalance.getCustomerId().toString())){
                return customerBalance;
            }
        }
        throw new Exception("Customer balance couldn't found");
    }
}
