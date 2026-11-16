package dummydata;

import model.*;
import repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DummyDataLoader {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;

    public DummyDataLoader(
            CompanyRepository companyRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            TransactionRepository transactionRepository) {

        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
    }

    public void load() {
        loadCompanies();
        loadProducts();
        loadOrders();
        loadTransactions();
    }



    private void loadCompanies() {

        Company[] companies = {
                new Company("C101", "Logitech", "9876500001", "Mumbai"),
                new Company("C102", "HP", "9876500002", "Bangalore"),
                new Company("C103", "Dell", "9876500003", "Hyderabad"),
                new Company("C104", "Samsung", "9876500004", "Chennai")
        };

        for (Company company : companies) {
            companyRepository.addCompany(company);
        }
    }



    private void loadProducts() {

        Product[] products = {
                new Product("P101", "Wireless Mouse", 25.0, "C101", ProductCategory.ACCESSORIES, 10),
                new Product("P102", "Mechanical Keyboard", 50.0, "C101", ProductCategory.ACCESSORIES, 5),
                new Product("P103", "USB Hub", 15.0, "C102", ProductCategory.ACCESSORIES, 0),
                new Product("P104", "Monitor", 200.0, "C103", ProductCategory.MONITORS, 8),
                new Product("P105", "SSD 1TB", 100.0, "C104", ProductCategory.STORAGE, 12),
                new Product("P106", "Laptop Stand", 30.0, "C102", ProductCategory.ACCESSORIES, 15),
                new Product("P107", "Webcam", 45.0, "C101", ProductCategory.ACCESSORIES, 7),
                new Product("P108", "External HDD", 80.0, "C104", ProductCategory.STORAGE, 9)
        };

        for (Product product : products) {
            productRepository.addProduct(product);
        }
    }



    private void loadOrders() {

        Order[] orders = {
                new Order(
                        "O101",
                        "Rahul",
                        createOrderItems(
                                new String[]{"P101", "P102"},
                                new int[]{2, 1},
                                new double[]{25.0, 50.0}
                        )
                ),

                new Order(
                        "O102",
                        "Priya",
                        createOrderItems(
                                new String[]{"P104", "P105"},
                                new int[]{1, 2},
                                new double[]{200.0, 100.0}
                        )
                ),

                new Order(
                        "O103",
                        "Kiran",
                        createOrderItems(
                                new String[]{"P106", "P107"},
                                new int[]{3, 1},
                                new double[]{30.0, 45.0}
                        )
                ),

                new Order(
                        "O104",
                        "Anjali",
                        createOrderItems(
                                new String[]{"P108", "P101"},
                                new int[]{2, 1},
                                new double[]{80.0, 25.0}
                        )
                ),

                new Order(
                        "O105",
                        "Ravi",
                        createOrderItems(
                                new String[]{"P102", "P107"},
                                new int[]{1, 2},
                                new double[]{50.0, 45.0}
                        )
                )
        };

        for (Order order : orders) {
            orderRepository.addOrder(order);
        }
    }



    private void loadTransactions() {

        Transaction[] transactions = {

                new Transaction(
                        "TX101",
                        "O101",
                        createOrderItems(
                                new String[]{"P101", "P102"},
                                new int[]{2, 1},
                                new double[]{25.0, 50.0}
                        ),
                        100.0,
                        LocalDateTime.now()
                ),

                new Transaction(
                        "TX102",
                        "O102",
                        createOrderItems(
                                new String[]{"P104", "P105"},
                                new int[]{1, 2},
                                new double[]{200.0, 100.0}
                        ),
                        400.0,
                        LocalDateTime.now()
                ),

                new Transaction(
                        "TX103",
                        "O103",
                        createOrderItems(
                                new String[]{"P106", "P107"},
                                new int[]{3, 1},
                                new double[]{30.0, 45.0}
                        ),
                        135.0,
                        LocalDateTime.now()
                ),

                new Transaction(
                        "TX104",
                        "O104",
                        createOrderItems(
                                new String[]{"P108", "P101"},
                                new int[]{2, 1},
                                new double[]{80.0, 25.0}
                        ),
                        185.0,
                        LocalDateTime.now()
                ),

                new Transaction(
                        "TX105",
                        "O105",
                        createOrderItems(
                                new String[]{"P102", "P107"},
                                new int[]{1, 2},
                                new double[]{50.0, 45.0}
                        ),
                        140.0,
                        LocalDateTime.now()
                )
        };

        for (Transaction transaction : transactions) {
            transactionRepository.addTransaction(transaction);
        }
    }



    private List<OrderItem> createOrderItems(
            String[] productIds,
            int[] quantities,
            double[] unitPrices) {

        List<OrderItem> items = new ArrayList<>();

        for (int i = 0; i < productIds.length; i++) {

            OrderItem item = new OrderItem(
                    productIds[i],
                    quantities[i],
                    unitPrices[i]
            );

            items.add(item);
        }

        return items;
    }
}
