package com.company;

import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;
import com.company.category.Category;
import com.company.discount.Discount;
import com.company.order.Order;
import com.company.order.OrderService;
import com.company.order.OrderServiceImpl;

import java.util.*;

import static com.company.DataGenerator.*;
import static com.company.StaticConstants.*;

public class Main {

    public static void main(String[] args) throws Exception {
        createCategory();
        createCustomer();
        createBalance();
        createProduct();
        createDiscount();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select customer: ");
        for (int i = 0; i < CUSTOMER_LIST.size(); i++) {
            System.out.println("Type " + i + " for customer: " + CUSTOMER_LIST.get(i).getUserName());
        }
        Customer customer = CUSTOMER_LIST.get(scanner.nextInt());
        String[] menuOptionList = prepareMenuOptions();

        Cart cart = new Cart(UUID.randomUUID());

        while (true) {
            System.out.println("What would you like to do ? Just type id for selection");
            for (int i = 0; i < menuOptionList.length; i++) {
                System.out.println(i + " - " + menuOptionList[i]);
            }
            int menuSelection = scanner.nextInt();
            switch (menuSelection) {
                case 0:
                    for (Category category : CATEGORY_LIST) {
                        System.out.println("category code: " + category.generateCategoryCode() + "category name: " + category.getName());
                    }
                    break;
                case 1:
                    try {
                        for (Product product : PRODUCT_LIST) {
                            System.out.println("product name: " + product.getName() + "product category name: " + product.getCategoryName());
                        }
                    }catch (Exception e){
                        System.out.println("Product couldn't printed because category not found product id: " + e.getMessage().split(",")[1]);
                    }
                    break;
                case 2:
                    for (Discount discount : DISCOUNT_LIST) {
                        System.out.println("discount name: " + discount.getName() + "discount threshold amount: " + discount.getThresholdAmount());
                    }
                    break;
                case 3:
                    CustomerBalance cBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance gBalance = findGiftCardBalance(customer.getId());
                    double totalBalance = cBalance.getBalance() + gBalance.getBalance();
                    System.out.println("Total balance : " + totalBalance);
                    System.out.println("Customer balance : " + cBalance.getBalance());
                    System.out.println("Gift Card balance : " + gBalance.getBalance());
                    break;
                case 4:
                    System.out.println("Which Account you would like to add: ");
                    CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());
                    System.out.println("Type 1 Customer balance: " + customerBalance.getBalance());
                    System.out.println("Type 2 GiftCart balance: " + giftCardBalance.getBalance());
                    int balanceAccountSelection = scanner.nextInt();
                    System.out.println("How much money you would like to add: ");
                    double additionalAmount = scanner.nextInt();

                    switch (balanceAccountSelection) {
                        case 1:
                            customerBalance.addBalance(additionalAmount);
                            System.out.println("New Customer balance: " + customerBalance.getBalance());
                        case 2:
                            giftCardBalance.addBalance(additionalAmount);
                            System.out.println("New Gift Card balance: " + customerBalance.getBalance());
                    }
                    break;
                case 5:
                    cart.setCustomerId(customer.getId());
                    Map<Product, Integer> map = new HashMap<>();
                    cart.setProductMap(map);
                    while (true) {
                        System.out.println("which product you would like add your cart. For exit product selection Type : exit");
                        for (Product product : PRODUCT_LIST) {
                            System.out.println("id : " + product.getId() + " price: " + product.getPrice() + " product category" + product.getCategoryName() + "stock :" + product.getRemainingStock() + "product will be delivered due" + product.getDeliveryDueDate());
                        }
                        String productId = scanner.next();
                        try {
                            Product product = findProductById(productId);
                            if (!putItemToCartIfStockAvailable(cart, product)){
                                System.out.println("stock is insufficient. Please try again." +
                                        " Available stock is " + product.getRemainingStock());
                                continue;
                            }
                        }catch (Exception e){
                            System.out.println("Product doesn't exist. Please try again.");
                            continue;
                        }
                        System.out.println("Do you want to add more product. Type Y for adding more, N for exit");
                        String decision = scanner.next();
                        if (!decision.equals("Y")){
                            break;
                        }

                    }
                    cart.setProductMap(map);


                    System.out.println("seems there are discount options. Do you want to see and apply to your cart if it is applicable. For no discount type no");
                    for (Discount discount : DISCOUNT_LIST){
                        System.out.println("discount id " + discount.getId() + " discount name: " + discount.getName());
                    }
                    String discountId = scanner.next();
                    if (!discountId.equals("no")){
                        Discount discount = findDiscountById(discountId);
                        if (discount != null){
                            if (discount.decideDiscountIsApplicableToCart(cart)){
                                cart.setDiscountId(discount.getId());
                            }
                        }
                    }

                    OrderService orderService = new OrderServiceImpl();
                    String result = orderService.placeOrder(customer, cart);
                    if (result.equals("Order has been placed successfully")){
                        System.out.println("Order is successful");
                        updateProductStock(cart.getProductMap());
                        cart.setProductMap(new HashMap<>());
                        cart.setDiscountId(null);
                    }else {
                        System.out.println(result);
                    }
                    break;
                case 6:
                    System.out.println("Your Cart");
                    if (cart.getProductMap().keySet().size() > 0){
                        for (Product product : cart.getProductMap().keySet()){
                            System.out.println("product name: " + product.getName() + " count: " + cart.getProductMap().get(product));
                        }
                    }else {
                        System.out.println("Your cart is empty");
                    }
                    break;
                case 7:
                    printOrdersByCustomerId(customer.getId());
                    break;
                case 8:
                    printOrdersByCustomerId(customer.getId());
                    break;
                case 9:
                    System.exit(1);
                    break;
            }
        }
    }

    public static boolean putItemToCartIfStockAvailable(Cart cart, Product product){
        System.out.println("Please provide product count: ");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Integer cartCount = cart.getProductMap().get(product);

        if ((cartCount != null && product.getRemainingStock() >  cartCount + count )
                ||  product.getRemainingStock() > count) {
            if (cart.getProductMap().containsKey(product)) {
                int itemCount = cart.getProductMap().get(product);
                cart.getProductMap().put(product, itemCount + count);
            } else {
                cart.getProductMap().put(product, count);
            }
            return true;
        }
        return false;
    }

    private static void printOrdersByCustomerId(UUID customerId){
        for (Order order : ORDER_LIST){
            if (order.getCustomerId().toString().equals(customerId.toString())){
                System.out.println("Order status: " + order.getOrderStatus() + " order amount " + order.getPaidAmount() + " order date " + order.getOrderDate());
            }
        }
    }

    private static void updateProductStock(Map<Product, Integer> map){
        for (Product product : map.keySet()){
            product.setRemainingStock( product.getRemainingStock() - map.get(product));
        }
    }

    private static Discount findDiscountById(String discountId){
        for (Discount discount : DISCOUNT_LIST){
            if (discount.getId().toString().equals(discountId)){
                return discount;
            }
        }
        return null;
    }

    private static Product findProductById(String productId) throws Exception {
        for (Product product : PRODUCT_LIST) {
            if (product.getId().toString().equals(productId)) {
                return product;
            }
        }
        throw new Exception("Product Not Found");
    }

    private static CustomerBalance findCustomerBalance(UUID customerId) {
        for (CustomerBalance customerBalance : CUSTOMER_BALANCE_LIST) {
            if (customerBalance.getCustomerId().toString().equals(customerId.toString())) {
                return customerBalance;
            }
        }
        CustomerBalance customerBalance = new CustomerBalance(0d, customerId);
        CUSTOMER_BALANCE_LIST.add(customerBalance);
        return customerBalance;
    }

    private static GiftCardBalance findGiftCardBalance(UUID customerId) {
        for (GiftCardBalance giftCardBalance : GIFT_CARD_BALANCE_LIST) {
            if (giftCardBalance.getCustomerId().toString().equals(customerId.toString())) {
                return giftCardBalance;
            }
        }
        GiftCardBalance giftCardBalance = new GiftCardBalance(0d, customerId);
        GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
        return giftCardBalance;
    }

    private static String[] prepareMenuOptions() {
        return new String[]{"List categories", "List products", "List Discounts",
                "See Balance", "Add Balance", "Place an order", "See Cart", "See order details", "See your addresses", "Close App"};
    }
}
