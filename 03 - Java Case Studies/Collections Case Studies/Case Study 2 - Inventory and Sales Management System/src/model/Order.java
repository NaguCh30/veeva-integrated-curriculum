package model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String orderId;
    private String customerName;
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;

    public Order(String orderId, String customerName, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderItems = orderItems;
        this.orderDate = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
