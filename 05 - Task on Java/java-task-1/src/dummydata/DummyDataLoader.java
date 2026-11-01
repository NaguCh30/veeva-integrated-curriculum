package dummydata;

import model.*;
import repository.*;
import service.ShipmentService;

import java.util.List;

public class DummyDataLoader {

    public void loadData(
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            CartRepository cartRepository,
            OrderRepository orderRepository,
            ShipmentRepository shipmentRepository) {

        // =========================================================
        // 1. CUSTOMERS
        // =========================================================

        Customer[] customers = {
                new Customer(101, "Arun Kumar", "arun@example.com",
                        "9876543210",
                        new Address("12 Park Street", "Hyderabad",
                                "Telangana", "500001", "India")),

                new Customer(102, "Priya Sharma", "priya@example.com",
                        "9876543211",
                        new Address("24 MG Road", "Vijayawada",
                                "Andhra Pradesh", "520010", "India")),

                new Customer(103, "Rahul Verma", "rahul@example.com",
                        "9876543212",
                        new Address("8 Lake View", "Chennai",
                                "Tamil Nadu", "600001", "India")),

                new Customer(104, "Sneha Reddy", "sneha@example.com",
                        "9876543213",
                        new Address("45 Green Avenue", "Bengaluru",
                                "Karnataka", "560001", "India")),

                new Customer(105, "Kiran Rao", "kiran@example.com",
                        "9876543214",
                        new Address("17 River Road", "Visakhapatnam",
                                "Andhra Pradesh", "530001", "India")),

                new Customer(106, "Vikram Singh", "vikram@example.com",
                        "9876543215",
                        new Address("21 Civil Lines", "Jaipur",
                                "Rajasthan", "302001", "India")),

                new Customer(107, "Ananya Das", "ananya@example.com",
                        "9876543216",
                        new Address("33 Salt Lake Road", "Kolkata",
                                "West Bengal", "700064", "India")),

                new Customer(108, "Rohit Mehta", "rohit@example.com",
                        "9876543217",
                        new Address("19 FC Road", "Pune",
                                "Maharashtra", "411004", "India")),

                new Customer(109, "Divya Nair", "divya@example.com",
                        "9876543218",
                        new Address("7 Marine Drive", "Kochi",
                                "Kerala", "682011", "India")),

                new Customer(110, "Aditya Patel", "aditya@example.com",
                        "9876543219",
                        new Address("42 SG Highway", "Ahmedabad",
                                "Gujarat", "380015", "India")),

                new Customer(111, "Meera Iyer", "meera@example.com",
                        "9876543220",
                        new Address("15 T Nagar", "Chennai",
                                "Tamil Nadu", "600017", "India")),

                new Customer(112, "Sanjay Gupta", "sanjay@example.com",
                        "9876543221",
                        new Address("28 Gomti Nagar", "Lucknow",
                                "Uttar Pradesh", "226010", "India"))
        };

        for (Customer customer : customers) {
            customerRepository.addCustomer(customer);
        }

        // =========================================================
        // 2. PRODUCTS
        // Product IDs: 501-550
        // =========================================================

        Product[] products = {
                // Computers and accessories
                new Product(501, "Laptop", 55000.00, "Dell"),
                new Product(502, "Wireless Mouse", 800.00, "Logitech"),
                new Product(503, "Mechanical Keyboard", 2500.00, "Logitech"),
                new Product(504, "Monitor", 12000.00, "Samsung"),
                new Product(505, "Headphones", 3500.00, "Sony"),
                new Product(506, "Webcam", 2200.00, "Logitech"),
                new Product(507, "USB Hub", 900.00, "Anker"),
                new Product(508, "External SSD", 6500.00, "Samsung"),
                new Product(509, "Bluetooth Speaker", 2800.00, "JBL"),
                new Product(510, "Tablet", 25000.00, "Lenovo"),

                // Mobile accessories
                new Product(511, "Phone Charger", 1200.00, "Anker"),
                new Product(512, "USB-C Cable", 450.00, "Anker"),
                new Product(513, "Power Bank", 1800.00, "Xiaomi"),
                new Product(514, "Phone Case", 600.00, "Spigen"),
                new Product(515, "Screen Protector", 300.00, "Spigen"),

                // Computer components
                new Product(516, "Graphics Card", 42000.00, "NVIDIA"),
                new Product(517, "RAM 16GB", 4200.00, "Corsair"),
                new Product(518, "Power Supply", 5500.00, "Corsair"),
                new Product(519, "Motherboard", 14000.00, "ASUS"),
                new Product(520, "CPU Cooler", 3200.00, "Cooler Master"),

                // Gaming products
                new Product(521, "Gaming Mouse", 1800.00, "Logitech"),
                new Product(522, "Gaming Headset", 4500.00, "HyperX"),
                new Product(523, "External HDD", 4800.00, "Western Digital"),
                new Product(524, "Wi-Fi Router", 2600.00, "TP-Link"),
                new Product(525, "Laser Printer", 16000.00, "HP"),

                // Additional laptops and displays
                new Product(526, "Business Laptop", 68000.00, "HP"),
                new Product(527, "Ultrawide Monitor", 28000.00, "LG"),
                new Product(528, "Laptop Stand", 1500.00, "Portronics"),
                new Product(529, "Docking Station", 7500.00, "Dell"),
                new Product(530, "Portable SSD", 8500.00, "SanDisk"),

                // Networking and storage
                new Product(531, "Mesh Wi-Fi System", 9500.00, "TP-Link"),
                new Product(532, "NAS Storage", 32000.00, "Synology"),
                new Product(533, "Ethernet Cable", 350.00, "D-Link"),
                new Product(534, "Network Switch", 2200.00, "Netgear"),
                new Product(535, "USB Flash Drive", 700.00, "SanDisk"),

                // Audio and video
                new Product(536, "Soundbar", 8500.00, "Sony"),
                new Product(537, "Studio Microphone", 6200.00, "Audio-Technica"),
                new Product(538, "Wireless Earbuds", 3200.00, "JBL"),
                new Product(539, "Action Camera", 18000.00, "GoPro"),
                new Product(540, "LED Projector", 24000.00, "Epson"),

                // Office and smart devices
                new Product(541, "Mechanical Pencil Set", 450.00, "Pentel"),
                new Product(542, "Office Chair", 9500.00, "Green Soul"),
                new Product(543, "Standing Desk", 18000.00, "ErgoYou"),
                new Product(544, "UPS", 4200.00, "APC"),
                new Product(545, "Smart Plug", 900.00, "TP-Link"),

                // Additional products
                new Product(546, "Smart Watch", 7500.00, "Samsung"),
                new Product(547, "Fitness Band", 2800.00, "Xiaomi"),
                new Product(548, "E-Reader", 12500.00, "Amazon"),
                new Product(549, "Drawing Tablet", 6800.00, "Wacom"),
                new Product(550, "Portable Monitor", 14500.00, "ASUS")
        };

        for (Product product : products) {
            productRepository.addProduct(product);
        }

        // =========================================================
        // 3. SHOPPING CARTS
        // Customers 101-110 have populated carts.
        // Customers 111-112 have empty carts until items are added.
        // =========================================================

        // Customer 101: Laptop + 2 Wireless Mice
        ShoppingCart cart101 = cartRepository.getShoppingCart(101);
        cart101.addItem(products[0], 1);
        cart101.addItem(products[1], 2);

        // Customer 102: Keyboard + Headphones + 2 USB Hubs
        ShoppingCart cart102 = cartRepository.getShoppingCart(102);
        cart102.addItem(products[2], 1);
        cart102.addItem(products[4], 1);
        cart102.addItem(products[6], 2);

        // Customer 103: Monitor + Webcam
        ShoppingCart cart103 = cartRepository.getShoppingCart(103);
        cart103.addItem(products[3], 1);
        cart103.addItem(products[5], 1);

        // Customer 104: Graphics Card + 2 RAM modules + Power Supply
        ShoppingCart cart104 = cartRepository.getShoppingCart(104);
        cart104.addItem(products[15], 1);
        cart104.addItem(products[16], 2);
        cart104.addItem(products[17], 1);

        // Customer 105: Tablet + Charger + 2 Power Banks
        ShoppingCart cart105 = cartRepository.getShoppingCart(105);
        cart105.addItem(products[9], 1);
        cart105.addItem(products[10], 1);
        cart105.addItem(products[12], 2);

        // Customer 106: Business Laptop + Docking Station
        ShoppingCart cart106 = cartRepository.getShoppingCart(106);
        cart106.addItem(products[25], 1);
        cart106.addItem(products[28], 1);

        // Customer 107: Ultrawide Monitor + Laptop Stand
        ShoppingCart cart107 = cartRepository.getShoppingCart(107);
        cart107.addItem(products[26], 1);
        cart107.addItem(products[27], 1);

        // Customer 108: Gaming Headset + Gaming Mouse
        ShoppingCart cart108 = cartRepository.getShoppingCart(108);
        cart108.addItem(products[21], 1);
        cart108.addItem(products[20], 2);

        // Customer 109: Soundbar + Wireless Earbuds
        ShoppingCart cart109 = cartRepository.getShoppingCart(109);
        cart109.addItem(products[35], 1);
        cart109.addItem(products[37], 1);

        // Customer 110: Smart Watch + Fitness Band
        ShoppingCart cart110 = cartRepository.getShoppingCart(110);
        cart110.addItem(products[45], 1);
        cart110.addItem(products[46], 1);

        // =========================================================
        // 4. HISTORICAL ORDERS
        // Keep IDs 1001 and 1002 for existing Main.java references.
        // New checkout orders should start at ID 1003.
        // =========================================================

        List<CartItem> order1001Items = List.of(
                new CartItem(products[0], 1),
                new CartItem(products[1], 2)
        );

        Order order1001 = new Order(
                1001,
                customers[0],
                order1001Items,
                100.0
        );

        orderRepository.addOrder(order1001);

        List<CartItem> order1002Items = List.of(
                new CartItem(products[2], 1),
                new CartItem(products[4], 1)
        );

        Order order1002 = new Order(
                1002,
                customers[1],
                order1002Items,
                100.0
        );

        orderRepository.addOrder(order1002);

        // =========================================================
        // 5. SHIPMENTS
        // Use ShipmentService so order status transitions are valid.
        // =========================================================

        ShipmentService shipmentService = new ShipmentService(
                orderRepository,
                shipmentRepository
        );

        // Order 1001: PLACED -> SHIPPED -> IN_TRANSIT
        shipmentService.dispatchOrder(
                1001,
                2001,
                "TRK-DL-1001",
                "Delhivery"
        );

        shipmentService.markInTransit(1001);

        // Order 1002: PLACED -> SHIPPED
        shipmentService.dispatchOrder(
                1002,
                2002,
                "TRK-BL-1002",
                "Blue Dart"
        );
    }
}