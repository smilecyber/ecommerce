package com.company.order;

import com.company.Cart;
import com.company.Customer;
import com.company.balance.Balance;

public interface OrderService {
    String placeOrder(Cart cart);
}
