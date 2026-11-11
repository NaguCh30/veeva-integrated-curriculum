# E-Commerce Product Management System

## Project Documentation

### 1. Project Overview

The E-Commerce Product Management System is a Core Java console-based application developed as part of the Veeva Integrated Curriculum.

The purpose of the project is to demonstrate object-oriented programming principles and implement the fundamental operations of an e-commerce application, including customer management, product catalog management, shopping carts, order processing, payment handling, and shipment tracking.

The application uses Java classes and in-memory collections to manage data. It does not require an external database or web framework.

### 2. Technologies Used

- **Programming Language:** Java
- **Java Concepts:** Object-Oriented Programming, Collections Framework, Interfaces, Abstract Classes, Enums, Exception Handling
- **Data Structures:** `HashMap`, `Map`, `List`, `ArrayList`
- **Storage:** In-memory data storage
- **Development Environment:** IntelliJ IDEA

  **Application Type:** Console-based application

No SQL database, Spring Framework, or external payment gateway is currently integrated.

### 3. Project Objectives

The main objectives are to:

1. Apply encapsulation, abstraction, inheritance, and polymorphism.
2. Manage customer information through repository operations.
3. Maintain a product catalog with multiple search criteria.
4. Implement shopping cart operations and price calculations.
5. Create orders from shopping carts.
6. Demonstrate different payment methods using a common interface.
7. Maintain order payment and delivery statuses.
8. Associate shipments with orders and provide tracking information.
9. Validate inputs and reject invalid operations.
10. Demonstrate application functionality through executable scenarios in `Main.java`.

### 4. Application Architecture

The project follows a simple layered structure to separate data models, data access operations, business logic, sample data, and application execution.

#### Model package

Contains the classes representing the application's core entities and payment abstractions.

- `Address` — stores address information.
- `Customer` — represents a customer and their personal information.
- `Product` — represents a catalog product.
- `CartItem` — represents a product and its quantity in a cart.
- `ShoppingCart` — manages a customer's cart items and total.
- `OrderItem` — represents an item included in an order.
- `Order` — represents an order, its items, shipping cost, payment status, and order status.
- `OrderStatus` — defines the supported order lifecycle states.
- `PaymentStatus` — defines payment states.
- `PaymentMethod` — common interface for payment implementations.
- `CardPayment` — abstract base class for card payments.
- `UpiPayment` — implements simulated UPI payment.
- `DebitCardPayment` — implements simulated debit-card payment.
- `CreditCardPayment` — implements simulated credit-card payment.
- `CashOnDeliveryPayment` — implements cash-on-delivery payment.
- `GiftCardPayment` — implements gift-card balance validation and deduction.
- `Shipment` — stores shipment and courier tracking information.

#### Repository package

Repositories manage in-memory data and provide operations to add, find, update, and delete records where implemented.

- `CustomerRepository`
- `ProductRepository`
- `CartRepository`
- `OrderRepository`
- `ShipmentRepository`
- `GiftCardRepository`

The repositories use Java collections, primarily maps keyed by IDs or other identifiers, to store application data during execution.

#### Service package

Service classes coordinate business operations across models and repositories.

- `CartService` — coordinates shopping cart operations.
- `OrderService` — validates checkout requests, creates orders, coordinates payment, saves successful orders, and clears the cart after successful checkout.
- `PaymentService` — validates payment requests and delegates payment execution to the selected payment implementation.
- `ShipmentService` — dispatches orders, creates shipment records, and advances supported delivery statuses.

#### Dummy data package

`DummyDataLoader` initializes sample customers, products, shopping carts, historical orders, and shipments.

This provides a repeatable dataset for demonstrating and validating the application.

#### Application entry point

`Main.java` initializes the repositories, loads sample data, creates the required services, and executes 30 demonstration scenarios.

### 5. Customer Management

The customer module manages personal information using encapsulated fields.

A customer contains:

- Customer ID
- Name
- Email address
- Phone number
- Address

The `Address` class maintains street, city, state, postal code, and country separately.

Implemented functionality includes:

- Adding customers.
- Finding a customer by ID.
- Searching customers by name.
- Searching customers by city.
- Finding customers by email.
- Finding customers by phone number.
- Updating customer information.
- Deleting customer records.
- Basic input validation.

Customer IDs are validated to be positive, and required fields are checked for valid values.

Customer registration and login are not part of the current implementation.

### 6. Product Catalog Management

The product module maintains a catalog containing product details.

Each product contains:

- Product ID
- Product name
- Price
- Company or manufacturer

Implemented functionality includes:

- Adding products.
- Finding products by ID.
- Searching by product name.
- Searching by product name and exact price.
- Searching by product name within a price range.
- Searching by product name and company.
- Searching by product name, price, and company.
- Updating product information.
- Deleting products.
- Basic product input validation.

The sample catalog contains 50 products, covering computers, accessories, mobile devices, networking equipment, audio devices, office products, and other electronics.

### 7. Shopping Cart Management

The shopping cart module allows each customer to maintain a collection of selected products.

Each cart item contains a product reference and quantity.

Implemented functionality includes:

- Retrieving a customer's cart.
- Adding a product to the cart.
- Increasing the quantity of an existing product.
- Updating a product's quantity.
- Removing a product from the cart.
- Calculating item subtotals.
- Calculating the total cart value.
- Clearing the cart after successful checkout.

The subtotal for a cart item is calculated as:

**Item subtotal = Product price × Quantity**

The cart total is calculated by summing the subtotals of all cart items.

The application validates product availability through the catalog when adding items and validates quantities to prevent invalid values.

### 8. Order Management

The order module converts a customer's shopping cart into an order.

An order contains:

- Order ID
- Customer reference
- List of order items
- Shipping cost
- Order status
- Payment status

When an order is created, its order items capture the product prices used for the order calculation.

The order subtotal is calculated by adding all order item subtotals.

**Order total = Order subtotal + Shipping cost**

The current demonstration uses a fixed shipping charge of ₹100 per order.

Implemented functionality includes:

- Creating orders during successful checkout.
- Retrieving an order by ID.
- Retrieving orders associated with a customer.
- Calculating order subtotal and total.
- Updating order status through validated transitions.
- Maintaining payment status.
- Deleting an order through the repository operation.

Order IDs `1001` and `1002` are used for preloaded historical orders. New checkout orders start from ID `1003`.

### 9. Payment Processing

The payment module demonstrates abstraction and polymorphism.

The `PaymentMethod` interface defines a common payment operation. Different payment classes implement this operation according to their payment type.

The supported payment methods are:

#### UPI payment

Validates the amount and simulates a successful payment.

#### Debit-card payment

Uses the `CardPayment` abstract base class and simulates a successful debit-card payment after validating the amount.

#### Credit-card payment

Uses the same abstract base class and simulates a successful credit-card payment after validating the amount.

#### Cash on delivery

Represents deferred payment. The order remains in `PENDING` payment status when checkout is completed using this method.

#### Gift-card payment

Maintains a gift-card balance. The payment succeeds only when the available balance covers the order amount. On success, the balance is reduced by the payment amount.

The `PaymentService` validates the payment request and delegates execution to the selected payment implementation.

For prepaid payment methods, successful checkout marks the payment status as `PAID`. Cash on delivery remains `PENDING`.

**Important limitation:** Payment processing is simulated. The application does not connect to banks, UPI providers, card networks, or external payment gateways.

### 10. Shipment Tracking

The shipment module associates a shipment with an order and stores courier information.

A shipment contains:

- Shipment ID
- Associated order
- Tracking ID
- Courier name

Implemented functionality includes:

- Creating a shipment when an order is dispatched.
- Searching for a shipment by shipment ID.
- Searching for a shipment by tracking ID.
- Finding a shipment associated with an order.
- Advancing an order to the in-transit status.
- Marking an order as delivered.
- Preventing invalid order-status transitions through order-level validation.

The demonstration includes the following tracking records:

- Order `1001` — tracking ID `TRK-DL-1001`, courier Delhivery.
- Order `1002` — tracking ID `TRK-BL-1002`, courier Blue Dart.

The current implementation stores shipment details but does not integrate with real courier APIs or provide live location updates.

### 11. Order and Payment Statuses

The `OrderStatus` enum defines these states:

- `PLACED`
- `SHIPPED`
- `IN_TRANSIT`
- `DELIVERED`
- `RETURNED`
- `CANCELLED`

The implemented order transition rules include:

- `PLACED` → `SHIPPED` or `CANCELLED`
- `SHIPPED` → `IN_TRANSIT` or `CANCELLED`
- `IN_TRANSIT` → `DELIVERED`
- `DELIVERED` → `RETURNED`

`RETURNED` and `CANCELLED` are terminal states in the current transition rules.

The `PaymentStatus` enum defines:

- `PENDING`
- `PAID`
- `FAILED`

The payment and order statuses are maintained separately because an order's delivery progress and its payment progress represent different aspects of the transaction.

### 12. Input Validation and Exception Handling

Validation is used to protect the application from invalid operations.

Examples include:

- Rejecting non-positive customer, product, order, and shipment IDs where applicable.
- Rejecting blank required text fields.
- Validating email format at a basic level.
- Rejecting invalid product prices and quantities.
- Rejecting null payment methods.
- Rejecting non-positive or non-finite payment amounts.
- Rejecting duplicate identifiers in repositories where implemented.
- Rejecting invalid order-status transitions.
- Rejecting gift-card payments when the available balance is insufficient.

`IllegalArgumentException` is used for invalid input, while `IllegalStateException` is used for certain operations that are invalid for the current application state.

### 13. Sample Data

The `DummyDataLoader` initializes:

- 12 customers.
- 50 products.
- 10 populated shopping carts.
- 2 historical orders.
- 2 shipment records.

The data is stored in memory and is recreated whenever the application starts.

Because there is no persistent database, changes made during one execution are not retained after the program terminates.

### 14. Validation Performed

The application was executed successfully, with exit code `0`.

The `Main.java` demonstration contains 30 numbered scenarios covering:

1. Customer lookup by ID.
2. Customer search by name.
3. Customer search by city.
4. Customer lookup by email.
5. Customer lookup by phone number.
6. Product lookup by ID.
7. Product search by name.
8. Product search by name and exact price.
9. Product search by name and price range.
10. Product search by name and company.
11. Product search by name, price, and company.
12. Product lookup from another category.
13. Shopping cart retrieval.
14. Cart total calculation.
15. Cart add, quantity update, and removal.
16. Retrieval of an empty cart.
17. Customer order history.
18. Order lookup by ID.
19. Shipment lookup by tracking ID.
20. Order delivery-status inspection.
21. UPI checkout.
22. Debit-card checkout.
23. Credit-card checkout.
24. Cash-on-delivery checkout.
25. Gift-card checkout.
26. Gift-card balance verification.
27. Payment-status verification.
28. Transition to in-transit status.
29. Transition to delivered status.
30. Final shipment and order-status verification.

The output confirmed successful execution of these scenarios using the loaded sample data.

This is a manual demonstration runner rather than a dedicated automated unit-testing suite. Additional tests are still recommended for invalid inputs, failed payments, duplicate records, and repository update/delete operations.

### 15. Current Scope and Limitations

The following features are not implemented in the current version or are not yet fully integrated:

- Customer registration and login.
- Password storage and authentication.
- Persistent database integration.
- Real payment gateway integration.
- Live courier tracking and tracking history.
- Automatic payment collection on delivery.
- Full return and refund processing.
- Gift-card issuance and administration workflows.
- Subscription plans with shipping benefits.
- Discount coupon generation and application.
- Automated unit tests and integration tests.
- A graphical user interface or web interface.

These are potential future enhancements and should not be considered completed features.

### 16. Future Enhancements

Possible future improvements include:

1. Implement customer registration and login with secure password hashing.
2. Introduce a persistent database.
3. Add automated unit and integration tests.
4. Implement payment failure and refund workflows.
5. Add order cancellation, returns, and refund management.
6. Add courier tracking history and timestamps.
7. Implement gift-card management and balance top-up operations.
8. Add subscription plans and shipping-fee benefits.
9. Implement discount coupons based on customer order history.
10. Add logging and more comprehensive error handling.

### 17. Conclusion

The project demonstrates the implementation of core e-commerce operations using Core Java and object-oriented design principles.

It separates models, repositories, and services to keep data representation, data access, and business logic organized. It also demonstrates collection-based storage, validation, order calculations, polymorphic payment handling, and shipment-status management.

The current version provides a functional foundation for an e-commerce product module and can be extended with persistence, authentication, automated testing, and additional business features in later development phases.
