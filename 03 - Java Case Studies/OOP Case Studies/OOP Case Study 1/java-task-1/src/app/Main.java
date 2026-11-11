package app;

import dummydata.DummyDataLoader;

import model.Address;
import model.Customer;
import model.Product;
import model.ShoppingCart;
import model.CartItem;
import model.Order;
import model.OrderItem;
import model.Shipment;
import model.GiftCardPayment;
import model.UpiPayment;
import model.DebitCardPayment;
import model.CreditCardPayment;
import model.CashOnDeliveryPayment;
import model.OrderStatus;

import repository.CustomerRepository;
import repository.ProductRepository;
import repository.CartRepository;
import repository.OrderRepository;
import repository.ShipmentRepository;
import repository.GiftCardRepository;

import service.CartService;
import service.OrderService;
import service.PaymentService;
import service.ShipmentService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // =========================================================
        // 1. INITIALIZE REPOSITORIES AND LOAD DUMMY DATA
        // =========================================================

        CustomerRepository customerRepository = new CustomerRepository();
        ProductRepository productRepository = new ProductRepository();
        CartRepository cartRepository = new CartRepository();
        OrderRepository orderRepository = new OrderRepository();
        ShipmentRepository shipmentRepository = new ShipmentRepository();
        GiftCardRepository giftCardRepository = new GiftCardRepository();

        DummyDataLoader loader = new DummyDataLoader();

        loader.loadData(
                customerRepository,
                productRepository,
                cartRepository,
                orderRepository,
                shipmentRepository
        );

        // Register a gift card for checkout demonstrations.
        GiftCardPayment giftCard = new GiftCardPayment(
                "GC-1001",
                60000.00
        );

        giftCardRepository.addGiftCard(giftCard);

        PaymentService paymentService = new PaymentService();

        CartService cartService = new CartService(
                customerRepository,
                productRepository,
                cartRepository
        );

        OrderService orderService = new OrderService(
                customerRepository,
                cartRepository,
                orderRepository,
                paymentService,
                giftCardRepository
        );

        ShipmentService shipmentService = new ShipmentService(
                orderRepository,
                shipmentRepository
        );

        System.out.println("=================================================");
        System.out.println(" E-COMMERCE APPLICATION - VALIDATION RUN");
        System.out.println("=================================================");

        // =========================================================
        // CUSTOMER REPOSITORY QUERIES
        // =========================================================

        // QUERY 1: Find a customer using customer ID.
        System.out.println("\n[QUERY 1] Find customer by ID");

        Customer customer = customerRepository.findById(101);

        printCustomer(customer);

        // QUERY 2: Find customers by name.
        System.out.println("\n[QUERY 2] Find customers by name");

        List<Customer> customersByName =
                customerRepository.findByName("Arun Kumar");

        printCustomers(customersByName);

        // QUERY 3: Find customers by city.
        System.out.println("\n[QUERY 3] Find customers by city");

        List<Customer> customersByCity =
                customerRepository.findByCity("Hyderabad");

        printCustomers(customersByCity);

        // QUERY 4: Find a customer by email.
        System.out.println("\n[QUERY 4] Find customer by email");

        Customer customerByEmail =
                customerRepository.findByEmail("priya@example.com");

        printCustomer(customerByEmail);

        // QUERY 5: Find a customer by phone number.
        System.out.println("\n[QUERY 5] Find customer by phone number");

        Customer customerByPhone =
                customerRepository.findByPhoneNumber("9876543212");

        printCustomer(customerByPhone);

        // =========================================================
        // PRODUCT REPOSITORY QUERIES
        // =========================================================

        // QUERY 6: Find a product using product ID.
        System.out.println("\n[QUERY 6] Find product by ID");

        Product productById = productRepository.findById(501);

        printProduct(productById);

        // QUERY 7: Find products by name.
        System.out.println("\n[QUERY 7] Find products by name");

        List<Product> productsByName =
                productRepository.findByName("Wireless Mouse");

        printProducts(productsByName);

        // QUERY 8: Find products by name and exact price.
        System.out.println("\n[QUERY 8] Find product by name and price");

        List<Product> productsByNameAndPrice =
                productRepository.findByNameAndPrice(
                        "Wireless Mouse",
                        800.00
                );

        printProducts(productsByNameAndPrice);

        // QUERY 9: Find products by name and price range.
        System.out.println("\n[QUERY 9] Find products within a price range");

        List<Product> productsByPriceRange =
                productRepository.findByNameAndPriceRange(
                        "Wireless Mouse",
                        500.00,
                        1000.00
                );

        printProducts(productsByPriceRange);

        // QUERY 10: Find products by name and company.
        System.out.println("\n[QUERY 10] Find products by name and company");

        List<Product> productsByCompany =
                productRepository.findByNameAndCompany(
                        "Wireless Mouse",
                        "Logitech"
                );

        printProducts(productsByCompany);

        // QUERY 11: Find products by name, price, and company.
        System.out.println(
                "\n[QUERY 11] Find products by name, price, and company"
        );

        List<Product> productsByNamePriceCompany =
                productRepository.findByNameAndPriceAndCompany(
                        "Wireless Mouse",
                        800.00,
                        "Logitech"
                );

        printProducts(productsByNamePriceCompany);

        // QUERY 12: Find a product from another category.
        System.out.println("\n[QUERY 12] Find a product from another category");

        printProduct(productRepository.findById(539));

        // =========================================================
        // SHOPPING CART VALIDATION
        // =========================================================

        // QUERY 13: Retrieve customer 101's shopping cart.
        System.out.println("\n[QUERY 13] Retrieve customer 101 cart");

        ShoppingCart cart101 = cartService.getCart(101);

        printCart(cart101);

        // QUERY 14: Calculate the cart total.
        System.out.println("\n[QUERY 14] Calculate cart total");

        System.out.printf("Customer 101 cart total: %.2f%n",
                cart101.getTotal());

        // QUERY 15: Add an item, update its quantity, and remove it.
        // The temporary item is removed at the end so the original
        // cart contents remain unchanged for the checkout demonstration.
        System.out.println(
                "\n[QUERY 15] Add, update, and remove a cart item"
        );

        Product temporaryCartProduct = productRepository.findById(512);

        cartService.addItem(101, 512, 1);

        System.out.println("After adding USB-C Cable:");
        printCart(cartService.getCart(101));

        cartService.updateQuantity(101, 512, 2);

        System.out.println("After changing USB-C Cable quantity to 2:");
        printCart(cartService.getCart(101));

        cartService.removeItem(101, 512);

        System.out.println("After removing USB-C Cable:");
        printCart(cartService.getCart(101));

        // QUERY 16: Retrieve an initially empty customer's cart.
        System.out.println("\n[QUERY 16] Retrieve customer 111 empty cart");

        ShoppingCart cart111 = cartService.getCart(111);

        printCart(cart111);

        // =========================================================
        // ORDER HISTORY AND SHIPMENT LOOKUPS
        // =========================================================

        // QUERY 17: Retrieve order history for customer 101.
        System.out.println("\n[QUERY 17] Customer 101 order history");

        List<Order> customer101Orders =
                orderRepository.findByCustomerId(101);

        printOrders(customer101Orders);

        // QUERY 18: Find an order using its order ID.
        System.out.println("\n[QUERY 18] Find order by ID");

        Order order1001 = orderRepository.findById(1001);

        printOrderSummary(order1001);

        // QUERY 19: Find a shipment using its tracking ID.
        System.out.println("\n[QUERY 19] Find shipment by tracking ID");

        Shipment shipment = shipmentService.trackShipment("TRK-DL-1001");

        printShipment(shipment);

        // QUERY 20: Inspect the current status of order 1001.
        System.out.println("\n[QUERY 20] Check order delivery status");

        if (order1001 != null) {
            System.out.println("Order ID: " + order1001.getOrderId());
            System.out.println("Status: " + order1001.getStatus());
        }

        // =========================================================
        // CHECKOUT AND PAYMENT VALIDATION
        // =========================================================

        // QUERY 21: Checkout customer 102 using UPI.
        System.out.println("\n[QUERY 21] Checkout using UPI");

        Order upiOrder = orderService.placeOrder(
                102,
                new UpiPayment("priya@upi")
        );

        printOrderSummary(upiOrder);

        // QUERY 22: Checkout customer 104 using a debit card.
        System.out.println("\n[QUERY 22] Checkout using debit card");

        Order debitCardOrder = orderService.placeOrder(
                104,
                new DebitCardPayment("4111111111111111")
        );

        printOrderSummary(debitCardOrder);

        // QUERY 23: Checkout customer 101 using a credit card.
        System.out.println("\n[QUERY 23] Checkout using credit card");

        Order creditCardOrder = orderService.placeOrder(
                101,
                new CreditCardPayment("5555555555554444")
        );

        printOrderSummary(creditCardOrder);

        // QUERY 24: Checkout customer 105 using cash on delivery.
        System.out.println("\n[QUERY 24] Checkout using cash on delivery");

        Order codOrder = orderService.placeOrder(
                105,
                new CashOnDeliveryPayment()
        );

        printOrderSummary(codOrder);

        // QUERY 25: Checkout customer 103 using a gift card.
        System.out.println("\n[QUERY 25] Checkout using a gift card");

        Order giftCardOrder = orderService.placeOrder(
                103,
                "GC-1001"
        );

        printOrderSummary(giftCardOrder);

        // QUERY 26: Check remaining gift card balance.
        System.out.println("\n[QUERY 26] Check gift card balance");

        GiftCardPayment savedGiftCard =
                giftCardRepository.findByGiftCardNumber("GC-1001");

        System.out.printf("Remaining gift card balance: %.2f%n",
                savedGiftCard.getBalance());

        // QUERY 27: Check the payment status of completed checkouts.
        System.out.println("\n[QUERY 27] Check payment statuses");

        System.out.println("UPI order: " + upiOrder.getPaymentStatus());
        System.out.println("Debit card order: "
                + debitCardOrder.getPaymentStatus());
        System.out.println("Credit card order: "
                + creditCardOrder.getPaymentStatus());
        System.out.println("COD order: " + codOrder.getPaymentStatus());
        System.out.println("Gift card order: "
                + giftCardOrder.getPaymentStatus());

        // =========================================================
        // SHIPMENT STATUS TRANSITIONS
        // =========================================================

        // QUERY 28: Move order 1002 from SHIPPED to IN_TRANSIT.
        System.out.println("\n[QUERY 28] Mark order 1002 in transit");

        shipmentService.markInTransit(1002);

        Order order1002 = orderRepository.findById(1002);

        System.out.println("Order 1002 status: " + order1002.getStatus());

        // QUERY 29: Mark order 1002 as DELIVERED.
        System.out.println("\n[QUERY 29] Mark order 1002 delivered");

        shipmentService.markDelivered(1002);

        System.out.println("Order 1002 status: " + order1002.getStatus());

        // QUERY 30: Check the final shipment and order status.
        System.out.println("\n[QUERY 30] Final shipment status check");

        Shipment secondShipment =
                shipmentService.trackShipment("TRK-BL-1002");

        printShipment(secondShipment);

        System.out.println("\n=================================================");
        System.out.println("VALIDATION DEMONSTRATION COMPLETED");
        System.out.println("=================================================");
    }

    // =============================================================
    // HELPER METHODS
    // =============================================================

    private static void printCustomer(Customer customer) {

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Phone: " + customer.getPhoneNumber());

        Address address = customer.getAddress();

        if (address != null) {
            System.out.println("City: " + address.getCity());
            System.out.println("State: " + address.getState());
        }
    }

    private static void printCustomers(List<Customer> customers) {

        if (customers == null || customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {
            System.out.println(
                    customer.getCustomerId() + " | "
                            + customer.getName() + " | "
                            + customer.getEmail() + " | "
                            + customer.getPhoneNumber()
            );
        }
    }

    private static void printProduct(Product product) {

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Name: " + product.getProductName());
        System.out.println("Company: " + product.getCompany());
        System.out.printf("Price: %.2f%n", product.getPrice());
    }

    private static void printProducts(List<Product> products) {

        if (products == null || products.isEmpty()) {
            System.out.println("No matching products found.");
            return;
        }

        for (Product product : products) {
            System.out.printf(
                    "ID: %d | Name: %s | Company: %s | Price: %.2f%n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getCompany(),
                    product.getPrice()
            );
        }
    }

    private static void printCart(ShoppingCart cart) {

        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        for (CartItem item : cart.getItems()) {
            System.out.printf(
                    "%s | Quantity: %d | Subtotal: %.2f%n",
                    item.getProduct().getProductName(),
                    item.getQuantity(),
                    item.getSubtotal()
            );
        }

        System.out.printf("Cart total: %.2f%n", cart.getTotal());
    }

    private static void printOrders(List<Order> orders) {

        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orders) {
            printOrderSummary(order);
            System.out.println("------------------------------");
        }
    }

    private static void printOrderSummary(Order order) {

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.printf("Subtotal: %.2f%n", order.getSubtotal());
        System.out.printf("Shipping: %.2f%n", order.getShippingCost());
        System.out.printf("Total: %.2f%n", order.getTotal());
        System.out.println("Order Status: " + order.getStatus());
        System.out.println("Payment Status: " + order.getPaymentStatus());
    }

    private static void printShipment(Shipment shipment) {

        if (shipment == null) {
            System.out.println("Shipment not found.");
            return;
        }

        System.out.println("Shipment ID: " + shipment.getShipmentId());
        System.out.println("Tracking ID: " + shipment.getTrackingId());
        System.out.println("Courier: " + shipment.getCourierName());
        System.out.println("Order ID: " + shipment.getOrder().getOrderId());
        System.out.println("Order Status: "
                + shipment.getOrder().getStatus());
    }
}
