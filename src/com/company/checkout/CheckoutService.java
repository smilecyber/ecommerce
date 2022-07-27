package com.company.checkout;

import com.company.Customer;
import com.company.order.Order;

public interface CheckoutService {
    boolean checkout(Customer customer, Double totalAmount);
}
