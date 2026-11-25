import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

class Order {
    private int orderId;
    private int customerId;
    private String category;
    private double amount;

    public Order (int orderId, int customerId, String category, double amount) {
        this.orderId = orderId;
         this.customerId = customerId;
         this.category = category;
         this.amount = amount;
    }

    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer Id: " + customerId + ", Category: " + category + ", Amount: " + amount;
    }
}

public class Q1OrderApplication {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(101, 1, "Electronics", 5000));
        orders.add(new Order(102, 2, "Clothing", 2000));
        orders.add(new Order(103, 1, "Grocery", 3000));
        orders.add(new Order(104, 3, "Electronics", 7000));
        orders.add(new Order(105, 2, "Grocery", 4000));
        orders.add(new Order(106, 4, "Clothing", -500));
        orders.add(new Order(107, 5, "Grocery", 0));
        orders.add(new Order(108, 3, "Clothing", 1000));

        // 1. Find orders with amount <= 0
        System.out.println("Invalid Orders:");
        orders.stream()
                .filter(order -> order.getAmount() <= 0)
                .forEach(System.out::println);

        System.out.println("\nTop 3 Highest Spending Customers:");
        orders.stream()
                .collect(
                        Collectors.groupingBy(
                                Order::getCustomerId,
                                Collectors.summingDouble(Order::getAmount)
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Double > comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }
}
