
package repository;

import model.Order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private final Map<String, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }

    public Map<String, Order> getAllOrders() {
        return Collections.unmodifiableMap(orders);
    }

}
