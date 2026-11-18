package service;

import exception.*;
import model.*;
import repository.*;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            TransactionRepository transactionRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction placeOrder(
            String orderId,
            String customerName,
            List<OrderItem> orderItems)
            throws InvalidNameException,
            InvalidProductIdException,
            ProductNotFoundException,
            InvalidStockUpdateException,
            InsufficientStockException,
            DuplicateOrderException {

        if (orderId == null || orderId.isBlank()) {
            throw new InvalidNameException("Please provide a valid Order ID.");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new InvalidNameException("Please provide a valid customer name.");
        }

        if (orderItems == null || orderItems.isEmpty()) {
            throw new InvalidNameException("An order must contain at least one product.");
        }

        if (orderRepository.getOrderById(orderId) != null) {
            throw new DuplicateOrderException(
                    "Order with ID " + orderId + " already exists.");
        }


        double totalAmount = 0;

        Map<Product, Integer> productsToUpdate = new LinkedHashMap<>();

        for (OrderItem item : orderItems) {

            if (item == null) {
                throw new InvalidNameException("Order contains an invalid item.");
            }

            String productId = item.getProductId();

            if (productId == null || productId.isBlank()) {
                throw new InvalidProductIdException("Please provide a valid Product ID.");
            }

            if (item.getQuantity() <= 0) {
                throw new InvalidStockUpdateException("Requested quantity must be greater than zero.");
            }

            Product product = productRepository.getProductById(productId);

            if (product == null) {
                throw new ProductNotFoundException("Product with ID " + productId + " not found.");
            }

            // Aggregate quantities if the same product appears multiple times
            int previousQuantity = productsToUpdate.getOrDefault(product, 0);
            int totalQuantity = Math.addExact(previousQuantity, item.getQuantity());

            productsToUpdate.put(product, totalQuantity);
        }

        // Check stock availability for every product
        for (Map.Entry<Product, Integer> entry : productsToUpdate.entrySet()) {

            Product product = entry.getKey();
            int requestedQuantity = entry.getValue();

            if (product.getAvailableStock() < requestedQuantity) {

                if (product.getAvailableStock() == 0) {
                    throw new InsufficientStockException(product.getProductName() + " is currently out of stock.");
                }

                throw new InsufficientStockException("Transaction failed. Only " +
                        product.getAvailableStock() +
                        " units of " + product.getProductName() +
                        " are available.");
            }
        }

        // Calculate bill using actual catalog prices
        for (OrderItem item : orderItems) {

            Product product = productRepository.getProductById(item.getProductId());
            totalAmount += product.getUnitPrice() * item.getQuantity();
        }

        // Deduct inventory after all validations succeed
        for (Map.Entry<Product, Integer> entry : productsToUpdate.entrySet()) {

            Product product = entry.getKey();
            int soldQuantity = entry.getValue();

            product.setAvailableStock(product.getAvailableStock() - soldQuantity);
        }

        // Create and store order
        Order order = new Order(orderId, customerName, orderItems);
        orderRepository.addOrder(order);

        // Create transaction
        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = new Transaction(
                transactionId,
                orderId,
                orderItems,
                totalAmount,
                LocalDateTime.now()
        );

        // Store transaction
        transactionRepository.addTransaction(transaction);

        return transaction;
    }
}
