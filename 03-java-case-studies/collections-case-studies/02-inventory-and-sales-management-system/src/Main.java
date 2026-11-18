import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dummydata.DummyDataLoader;
import model.*;
import repository.*;
import service.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static CompanyRepository companyRepository;
    private static ProductRepository productRepository;
    private static OrderRepository orderRepository;
    private static TransactionRepository transactionRepository;

    private static CompanyService companyService;
    private static ProductService productService;
    private static OrderService orderService;

    public static void main(String[] args) {

        initialize();

        int choice;

        do {
            showMenu();

            try {
                choice = readInt("Enter your choice: ");

                switch (choice) {

                    case 1 -> addCompany();
                    case 2 -> updateCompany();
                    case 3 -> deleteCompany();
                    case 4 -> searchCompany();
                    case 5 -> displayAllCompanies();

                    case 6 -> addProduct();
                    case 7 -> updateProduct();
                    case 8 -> deleteProduct();
                    case 9 -> searchProduct();
                    case 10 -> displayAllProducts();

                    case 11 -> lookupProductPrice();
                    case 12 -> checkProductStock();

                    case 13 -> placeOrder();
                    case 14 -> displayAllOrders();
                    case 15 -> displayTransactionHistory();

                    case 0 -> System.out.println("Exiting application...");

                    default -> System.out.println("Invalid choice. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }


    private static void initialize() {

        companyRepository = new CompanyRepository();
        productRepository = new ProductRepository();
        orderRepository = new OrderRepository();
        transactionRepository = new TransactionRepository();

        DummyDataLoader loader = new DummyDataLoader(
                companyRepository,
                productRepository,
                orderRepository,
                transactionRepository
        );

        loader.load();

        companyService = new CompanyService(companyRepository);

        productService = new ProductService(
                productRepository,
                companyRepository
        );

        orderService = new OrderService(
                orderRepository,
                productRepository,
                transactionRepository
        );
    }


    private static void showMenu() {

        System.out.println("\n========== INVENTORY & SALES MANAGEMENT ==========");

        System.out.println("\n1. Add Company");
        System.out.println("2. Update Company");
        System.out.println("3. Delete Company");
        System.out.println("4. Search Company");
        System.out.println("5. Display All Companies");

        System.out.println("\n6. Add Product");
        System.out.println("7. Update Product");
        System.out.println("8. Delete Product");
        System.out.println("9. Search Product");
        System.out.println("10. Display All Products");

        System.out.println("\n11. Product Price Lookup");
        System.out.println("12. Check Product Stock");

        System.out.println("\n13. Place Customer Order");
        System.out.println("14. Display All Orders");
        System.out.println("15. Display Transaction History");

        System.out.println("\n0. Exit");

        System.out.println("===================================================");
    }


    private static void addCompany() throws Exception {

        System.out.println("\n========== ADD COMPANY ==========");

        String id = readString("Enter Company ID: ");
        String name = readString("Enter Company Name: ");
        String contact = readString("Enter Company Contact: ");
        String location = readString("Enter Company Location: ");

        companyService.addCompany(id, name, contact, location);

        System.out.println("Company added successfully.");
    }

    private static void updateCompany() throws Exception {

        System.out.println("\n========== UPDATE COMPANY ==========");

        String id = readString("Enter Company ID: ");
        String name = readString("Enter New Company Name: ");
        String contact = readString("Enter New Contact: ");
        String location = readString("Enter New Location: ");

        companyService.updateCompany(id, name, contact, location);

        System.out.println("Company updated successfully.");
    }

    private static void deleteCompany() throws Exception {

        System.out.println("\n========== DELETE COMPANY ==========");

        String id = readString("Enter Company ID: ");

        companyService.deleteCompany(id);

        System.out.println("Company deleted successfully.");
    }

    private static void searchCompany() throws Exception {

        System.out.println("\n========== SEARCH COMPANY ==========");

        String id = readString("Enter Company ID: ");

        Company company = companyService.getCompanyByID(id);

        displayCompany(company);
    }

    private static void displayAllCompanies() {

        System.out.println("\n========== ALL COMPANIES ==========");

        Map<String, Company> companies =
                companyRepository.getAllCompanies();

        if (companies.isEmpty()) {
            System.out.println("No companies found.");
            return;
        }

        for (Company company : companies.values()) {
            displayCompany(company);
        }
    }

    private static void displayCompany(Company company) {

        System.out.println("-----------------------------------");
        System.out.println("Company ID:       " + company.getCompanyId());
        System.out.println("Company Name:     " + company.getCompanyName());
        System.out.println("Company Contact:  " + company.getCompanyContact());
        System.out.println("Company Location: " + company.getCompanyLocation());
        System.out.println("-----------------------------------");
    }

    private static void addProduct() throws Exception {

        System.out.println("\n========== ADD PRODUCT ==========");

        String id = readString("Enter Product ID: ");
        String name = readString("Enter Product Name: ");
        double price = readDouble("Enter Unit Price: ");
        String companyId = readString("Enter Supplier Company ID: ");
        ProductCategory category = readCategory();
        int stock = readInt("Enter Available Stock: ");

        productService.addProduct(
                id, name, price, companyId, category, stock
        );

        System.out.println("Product added successfully.");
    }

    private static void updateProduct() throws Exception {

        System.out.println("\n========== UPDATE PRODUCT ==========");

        String id = readString("Enter Product ID: ");

        Product product = productService.getProductById(id);

        System.out.println("Current Product Details:");
        displayProduct(product);

        String name = readString("Enter New Product Name: ");
        double price = readDouble("Enter New Unit Price: ");
        ProductCategory category = readCategory();
        int stock = readInt("Enter New Available Stock: ");

        productService.updateProduct(
                id, name, price, category, stock
        );

        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct() throws Exception {

        System.out.println("\n========== DELETE PRODUCT ==========");

        String id = readString("Enter Product ID: ");

        productService.deleteProductById(id);

        System.out.println("Product deleted successfully.");
    }

    private static void searchProduct() throws Exception {

        System.out.println("\n========== SEARCH PRODUCT ==========");

        String id = readString("Enter Product ID: ");

        Product product = productService.getProductById(id);

        displayProduct(product);
    }

    private static void displayAllProducts() {

        System.out.println("\n========== ALL PRODUCTS ==========");

        Map<String, Product> products =
                productRepository.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product product : products.values()) {
            displayProduct(product);
        }
    }

    private static void displayProduct(Product product) {

        System.out.println("-----------------------------------");
        System.out.println("Product ID:       " + product.getProductId());
        System.out.println("Product Name:     " + product.getProductName());
        System.out.println("Unit Price:       " + product.getUnitPrice());
        System.out.println("Company ID:       " + product.getCompanyID());
        System.out.println("Category:         " + product.getProductCategory());
        System.out.println("Available Stock:  " + product.getAvailableStock());
        System.out.println("-----------------------------------");
    }

    private static void lookupProductPrice() throws Exception {

        System.out.println("\n========== PRODUCT PRICE LOOKUP ==========");

        String id = readString("Enter Product ID: ");

        ProductInfo productInfo =
                productService.getPriceDetailsByProductId(id);

        System.out.println(productInfo);
    }

    private static void checkProductStock() throws Exception {

        System.out.println("\n========== CHECK PRODUCT STOCK ==========");

        String id = readString("Enter Product ID: ");

        Product product = productService.getProductById(id);

        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Available Stock: " + product.getAvailableStock());
    }


    private static void placeOrder() throws Exception {

        System.out.println("\n========== PLACE CUSTOMER ORDER ==========");

        String orderId = readString("Enter Order ID: ");
        String customerName = readString("Enter Customer Name: ");

        int numberOfProducts = readInt("Enter Number of Products: ");

        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 1; i <= numberOfProducts; i++) {

            System.out.println("\nProduct " + i);

            String productId = readString("Enter Product ID: ");
            int quantity = readInt("Enter Quantity: ");

            double unitPrice = productService.getProductById(productId).getUnitPrice();

            orderItems.add(new OrderItem(productId, quantity, unitPrice));
        }

        Transaction transaction = orderService.placeOrder(
                orderId,
                customerName,
                orderItems
        );

        System.out.println("\nOrder placed successfully!");
        System.out.println(transaction);
    }

    private static void displayAllOrders() {

        System.out.println("\n========== ALL ORDERS ==========");

        Map<String, Order> orders =
                orderRepository.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orders.values()) {

            System.out.println("-----------------------------------");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Order Date: " + order.getOrderDate());

            System.out.println("Ordered Items:");

            for (OrderItem item : order.getOrderItems()) {
                System.out.println(
                        "Product ID: " + item.getProductId()
                                + " | Quantity: " + item.getQuantity()
                                + " | Unit Price: " + item.getUnitPrice()
                );
            }

            System.out.println("-----------------------------------");
        }
    }

    private static void displayTransactionHistory() {

        System.out.println("\n========== TRANSACTION HISTORY ==========");

        Map<String, Transaction> transactions =
                transactionRepository.getAllTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions.values()) {

            System.out.println(transaction);

            System.out.println("Sold Items:");

            for (OrderItem item : transaction.getSoldItems()) {
                System.out.println(
                        "Product ID: " + item.getProductId()
                                + " | Quantity: " + item.getQuantity()
                                + " | Unit Price: " + item.getUnitPrice()
                );
            }

            System.out.println("-----------------------------------");
        }
    }

    private static String readString(String message) {

        System.out.print(message);
        return sc.nextLine().trim();
    }

    private static int readInt(String message) {

        System.out.print(message);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private static double readDouble(String message) {

        System.out.print(message);
        return Double.parseDouble(sc.nextLine().trim());
    }

    private static ProductCategory readCategory() {

        System.out.println("Available Categories:");

        for (ProductCategory category : ProductCategory.values()) {
            System.out.println(category);
        }

        String category = readString("Enter Product Category: ");

        return ProductCategory.valueOf(category.toUpperCase());
    }
}
