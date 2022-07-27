package com.company;

import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;
import com.company.category.Category;
import com.company.category.Electronic;
import com.company.category.Furniture;
import com.company.category.SkinCare;
import com.company.discount.AmountBasedDiscount;
import com.company.discount.Discount;
import com.company.discount.RateBasedDiscount;

import java.util.*;

import static com.company.StaticConstants.*;
import static com.company.StaticConstants.CUSTOMER_LIST;

public class DataGenerator {

    public static void createDiscount() {
        Discount amountBasedDiscount = new AmountBasedDiscount(UUID.randomUUID(), "Buy 250 Free 50", 250d, 50d);
        DISCOUNT_LIST.add(amountBasedDiscount);

        Discount rateBasedDiscount = new RateBasedDiscount(UUID.randomUUID(),
                "Buy 500 Free %15", 500d,
                new HashSet<>(Arrays.asList(CATEGORY_LIST.get(0).getName(), CATEGORY_LIST.get(1).getName())), 15d);

        DISCOUNT_LIST.add(rateBasedDiscount);
    }

    public static void createBalance() {
        CustomerBalance customerBalance = new CustomerBalance(450d, CUSTOMER_LIST.get(0).getId());
        CUSTOMER_BALANCE_LIST.add(customerBalance);
        GiftCardBalance giftCardBalance = new GiftCardBalance(550d, CUSTOMER_LIST.get(1).getId());
        GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
    }

    public static void createCategory() {
        Category category = new Electronic(UUID.randomUUID(), "Electronic");
        Category category2 = new Furniture(UUID.randomUUID(), "Furniture");
        Category category3 = new SkinCare(UUID.randomUUID(), "SkinCare");
        CATEGORY_LIST.add(category);
        CATEGORY_LIST.add(category2);
        CATEGORY_LIST.add(category3);
    }

    public static void createProduct() {
        Category electronicCategory = CATEGORY_LIST.get(0);
        Category furnitureCategory = CATEGORY_LIST.get(1);
        Category skinCareCategory = CATEGORY_LIST.get(2);
        Product product1 = new Product(UUID.randomUUID(), "PS5", 230d, 250d, 7, 7, electronicCategory.getId());
        Product product2 = new Product(UUID.randomUUID(), "XBOX", 120d, 180d, 15, 15, electronicCategory.getId());
        Product product3 = new Product(UUID.randomUUID(), "Chair", 30d, 50d, 85, 85, furnitureCategory.getId());
        Product product4 = new Product(UUID.randomUUID(), "Face Creme", 6d, 8d, 250, 250, skinCareCategory.getId());

        PRODUCT_LIST.add(product1);
        PRODUCT_LIST.add(product2);
        PRODUCT_LIST.add(product3);
        PRODUCT_LIST.add(product4);
    }

    public static void createCustomer() {
        Address addressCustomer1 = new Address("Home", "dummy street", "zipcode", "add", "virginia");
        Address addressCustomer2 = new Address("Work", "dummy street", "zipcode", "add", "virginia");
        List<Address> customer1AddressList = new ArrayList<>();
        customer1AddressList.add(addressCustomer1);
        customer1AddressList.add(addressCustomer2);
        Customer customer1 = new Customer(UUID.randomUUID(), "email@email.com", "msmith", customer1AddressList);
        Customer customer2 = new Customer(UUID.randomUUID(), "email1@email.com", "jane");
        CUSTOMER_LIST.add(customer1);
        CUSTOMER_LIST.add(customer2);
    }
}
