import java.util.List;
import java.util.Comparator;

class Order {
    private int orderId;
    private String customerName;
    private List<OrderItem> items;

    public Order (int orderId, String customerName, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public List<OrderItem> getItems() { return items; }
}

class OrderItem {
    private String productName;
    private int quantity;
    private double price;

    public OrderItem(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return productName + " | " + quantity + " | " + price;
    }
}

class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order(101, "Ravi", List.of(
                new OrderItem("Laptop", 1, 60000),
                new OrderItem("Mouse", 2, 500)
            )),
            new Order(102, "Priya", List.of(
                new OrderItem("Mobile", 2, 25000),
                new OrderItem("Headphones", 1, 2000)
            )),
            new Order(103, "Kiran", List.of(
                new OrderItem("Keyboard", 1, 1500),
                new OrderItem("Monitor", 2, 12000)
            ))
        );

        
        System.out.println("Extract all customer names.");
        orders.stream()
            .map(Order::getCustomerName)
            .forEach(System.out::println);

        System.out.println("\nExtract all order items from all orders using flatMap().");
        orders.stream()
            .map(Order::getItems)
            .flatMap(List::stream)
            .map(OrderItem::getProductName)
            .forEach(System.out::println);

        System.out.println("\nCalculate the total number of products sold using flatMapToInt() and mapToInt().");
        int totalOrderedProducts = orders.stream()
            .map(Order::getItems)
            .flatMap(List::stream)
            .mapToInt(OrderItem::getQuantity)
            .sum();
        System.out.println("Total number of orders: " + totalOrderedProducts);

        System.out.println("\nCalculate the total revenue generated from all orders. (quantity * price)");
        double totalRevenue = orders.stream()
            .flatMap(o -> o.getItems()
                     .stream())
            .mapToDouble(oi -> oi.getQuantity() * oi.getPrice())
            .sum();
        System.out.println("Total Reveue: " + totalRevenue);

        System.out.println("\nFind all products whose total item value (quantity × price) exceeds 10,000.");
        orders.stream()
            .map(Order::getItems)
            .flatMap(List::stream)
            .filter(oi -> oi.getQuantity() * oi.getPrice() > 10000)
            .map(OrderItem::getProductName)
            .forEach(System.out::println);

        System.out.println("\nFind the most expensive order item based on its total item value.");
        OrderItem mostExpensiveItem = orders.stream()
            .map(Order::getItems)
            .flatMap(List::stream)
            .max(Comparator.comparingDouble(
                oi -> oi.getPrice() * oi.getQuantity()
            ))
            .orElse(null);
        System.out.println(mostExpensiveItem);

        System.out.println("\nCalculate the average order value.");
        double averageOrderValue = orders.stream()
            .mapToDouble(order ->
                order.getItems().stream()
                    .mapToDouble(item ->
                        item.getPrice() * item.getQuantity()
                    )
                    .sum()
            )
            .average()
            .orElse(0.0);
        System.out.println("Average order value: "+ averageOrderValue);

        System.out.println("\nFind all customers whose order value exceeds 30,000.");
        orders.stream()
            .filter(order -> order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum() > 30000)
            .map(Order::getCustomerName)
            .forEach(System.out::println);
    }
}