package repository;

import model.Order;
import model.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Provide valid order details!");
        }
        if (orders.containsKey(order.getOrderId())) {
            throw new IllegalArgumentException("Order with ID " + order.getOrderId() + " already exists!");
        }

        orders.put(order.getOrderId(), order);
    }

    public Order findById(long orderId) {
        return orders.get(orderId);
    }

    public List<Order> findByCustomerId(long customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Provide valid customer ID!");
        }

        List<Order> matchingOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomer().getCustomerId() == customerId) {
                matchingOrders.add(order);
            }
        }

        return matchingOrders;
    }

    public void updateOrderStatus(long orderId, OrderStatus newStatus) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Please provide a valid Order ID!");
        }

        if (newStatus == null) {
            throw new IllegalArgumentException("Please provide a valid status!");
        }

        if (!orders.containsKey(orderId)) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exists!");
        }

        Order order = orders.get(orderId);
        order.updateStatus(newStatus);
    }

    public void deleteOrder(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be greater than zero!");
        }
        if (!orders.containsKey(orderId)) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exists!");
        }

        orders.remove(orderId);
    }
}
