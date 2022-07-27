package com.company.order;

import com.company.Address;
import com.company.Cart;
import com.company.Customer;
import com.company.Product;
import com.company.checkout.CheckoutService;
import com.company.checkout.CustomerBalanceCheckoutServiceImpl;
import com.company.checkout.MixPaymentCheckoutServiceImpl;
import com.company.discount.Discount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static com.company.StaticConstants.*;

public class OrderServiceImpl implements OrderService{
    @Override
    public String placeOrder(Customer customer, Cart cart) {
        List<Address> addressList = customer.getAddressList();
        Scanner scanner = new Scanner(System.in);
        if (addressList.size() <= 0){
            System.out.println("Address can not be found. Please provide an address.");
            System.out.println("Name: ");
            String name = scanner.next();
            System.out.println("Street: ");
            String street = scanner.next();
            System.out.println("zipCode: ");
            String zipCode = scanner.next();
            System.out.println("additionalAddressLine: ");
            String additionalAddressLine = scanner.next();
            System.out.println("Region: ");
            String region = scanner.next();
            Address address = new Address(name, street, zipCode ,additionalAddressLine, region);
            customer.getAddressList().add(address);
            ADDRESS_LIST.add(address);
        }
        double amountAfterDiscount = cart.calculateCartTotalAmount();

        if (cart.getDiscountId() != null){
            Discount discount = findDiscountById(cart.getDiscountId());
            amountAfterDiscount = discount.calculateCartAmountAfterDiscountApplied(amountAfterDiscount);
        }

        System.out.println("which payment option you would like to choose, Type 1 : customer balance, Type 2 : Mix (gift card + customer balance)");
        int paymentType = scanner.nextInt();
        boolean checkoutResult = false;
        switch (paymentType){
            case 1:
                CheckoutService customerBalanceCheckoutService = new CustomerBalanceCheckoutServiceImpl();
                checkoutResult = customerBalanceCheckoutService.checkout(customer, amountAfterDiscount);
                break;
            case 2:
                CheckoutService mixPaymentCheckoutService = new MixPaymentCheckoutServiceImpl();
                checkoutResult = mixPaymentCheckoutService.checkout(customer, amountAfterDiscount);
                break;
        }


        if (checkoutResult){
            Order order = new Order(UUID.randomUUID(), LocalDateTime.now(),
                    cart.calculateCartTotalAmount(), amountAfterDiscount,
                    cart.calculateCartTotalAmount() - amountAfterDiscount, customer.getId()
                    , "Placed", cart.getProductMap().keySet());
            ORDER_LIST.add(order);
            return "Order has been placed successfully";
        }else {
            return "Balance is insufficient. Please add money to your one of balances and try again.";
        }
    }

    private Discount findDiscountById(UUID discountId){
        for (Discount discount : DISCOUNT_LIST){
            if (discount.getId().toString().equals(discountId.toString())){
                return discount;
            }
        }
        return null;
    }
}
