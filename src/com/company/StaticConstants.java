package com.company;

import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;
import com.company.category.Category;
import com.company.discount.Discount;
import com.company.order.Order;

import java.util.ArrayList;
import java.util.List;

public class StaticConstants {
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();
    public static final List<Category> CATEGORY_LIST = new ArrayList<>();
    public static final List<Order> ORDER_LIST = new ArrayList<>();
    public static final List<Customer> CUSTOMER_LIST = new ArrayList<>();
    public static final List<Address> ADDRESS_LIST = new ArrayList<>();
    public static final List<Discount> DISCOUNT_LIST = new ArrayList<>();
    public static final List<CustomerBalance> CUSTOMER_BALANCE_LIST = new ArrayList<>();
    public static final List<GiftCardBalance> GIFT_CARD_BALANCE_LIST = new ArrayList<>();

}
