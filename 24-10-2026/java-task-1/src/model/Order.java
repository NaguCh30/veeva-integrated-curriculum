package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final long orderId;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private final double shippingCost;

    public Order(long orderId, Customer customer, List<CartItem> cartItems, double shippingCost) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID should be greater than zero!");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Provide valid customer details!");
        }
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Provide valid cart items!");
        }

        if (shippingCost < 0) {
            throw new IllegalArgumentException("Shipping Cost must be greater than zero!");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem(item.getProduct(), item.getQuantity());
            orderItems.add(orderItem);
        }

        this.orderId = orderId;
        this.customer = customer;
        this.items = orderItems;
        this.status = OrderStatus.PLACED;
        this.shippingCost = shippingCost;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }

        return subtotal;
    }

    public double getTotal() {
        double total = getSubtotal();
        total += this.shippingCost;
        return total;
    }

    public long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public List<OrderItem> getItems() {
        List<OrderItem> orderItems = new ArrayList<>(items);
        return orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Order Status should not be null!");
        }

        if (this.status == OrderStatus.PLACED && !(newStatus == OrderStatus.SHIPPED || newStatus == OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Invalid new status update for the current status!");
        } else if (this.status == OrderStatus.SHIPPED && !(newStatus == OrderStatus.IN_TRANSIT || newStatus == OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Invalid new status update for the current status!");
        } else if (this.status == OrderStatus.IN_TRANSIT && !(newStatus == OrderStatus.DELIVERED)) {
            throw new IllegalStateException("Invalid new status update for the current status!");
        } else if (this.status == OrderStatus.DELIVERED && !(newStatus == OrderStatus.RETURNED)) {
            throw new IllegalStateException("Invalid status transition from DELIVERED!");
        } else if (this.status == OrderStatus.RETURNED) {
            throw new IllegalStateException("This order was already returned!");
        } else if (this.status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("This order was already cancelled!");
        }

        this.status = newStatus;
    }

    public void updatePaymentStatus(PaymentStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Payment status cannot be null!");
        }

        this.paymentStatus = newStatus;
    }
}
