package service;

import model.*;
import repository.CartRepository;
import repository.CustomerRepository;
import repository.GiftCardRepository;
import repository.OrderRepository;

import java.util.List;

public class OrderService {
    private long nextOrderId = 1003;

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final GiftCardRepository giftCardRepository;

    public OrderService(CustomerRepository customerRepository,
                           CartRepository cartRepository,
                           OrderRepository orderRepository,
                            PaymentService paymentService,
                            GiftCardRepository giftCardRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.giftCardRepository = giftCardRepository;
    }

    public Order placeOrder(long customerId, PaymentMethod paymentMethod) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Provide a valid payment method!");
        }

        ShoppingCart cart = cartRepository.getShoppingCart(customerId);
        List<CartItem> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Cannot place order!");
        }

        Order order = new Order(nextOrderId, customer, cartItems, 100.0);
        boolean paymentSuccessful = paymentService.processPayment(paymentMethod, order.getTotal());
        if(!paymentSuccessful) {
            throw new IllegalStateException("Payment failed!");
        }
        if(!paymentMethod.isDeferredPayment()) {
            order.updatePaymentStatus(PaymentStatus.PAID);
        }
        orderRepository.addOrder(order);
        nextOrderId++;
        cart.clear();
        return order;
    }

    public Order placeOrder(long customerId, String giftCardNumber) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }
        if (giftCardNumber == null || giftCardNumber.isBlank()) {
            throw new IllegalArgumentException("Provide valid Gift Card Number!");
        }
        GiftCardPayment giftCard = giftCardRepository.findByGiftCardNumber(giftCardNumber);
        if (giftCard == null) {
            throw new IllegalArgumentException("Gift Card with Number " + giftCardNumber + " does not exist!");
        }

        ShoppingCart cart = cartRepository.getShoppingCart(customerId);
        List<CartItem> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Cannot place order!");
        }

        Order order = new Order(nextOrderId, customer, cartItems, 100.0);
        boolean paymentSuccessful = paymentService.processPayment(giftCard, order.getTotal());
        if(!paymentSuccessful) {
            throw new IllegalStateException("Gift Card payment failed!");
        }
        order.updatePaymentStatus(PaymentStatus.PAID);
        orderRepository.addOrder(order);
        nextOrderId++;
        cart.clear();
        return order;
    }
}
