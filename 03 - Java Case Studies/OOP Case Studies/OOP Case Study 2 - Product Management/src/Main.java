import exceptions.*;
import model.Product;
import repository.ProductRepository;
import service.ProductService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final ProductService service = new ProductService(productRepository);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------- MENU ----------");
        System.out.println("1. Add a product");
        System.out.println("2. Update a product");
        System.out.println("3. Search a product by Product ID");
        System.out.println("4. Delete a product by Product ID");
        System.out.println("5. Get all products based on Price in Descending Order");
        System.out.println("6. Get all products based on given Price Range in Descending Order");
        System.out.println("7. Get all products based on Price and Manufactung date descending Order");
        System.out.println("8. Get all products matching to a specific name");
        System.out.println("9. Get all products manufactured on specific date");
        System.out.println("10. Get all products matching to a specific price");
        System.out.println("11. Get all products with specific name, company, and price");
        System.out.println("12. Get top k products based on price");
        System.out.println("13. Get least k products based on price");
        System.out.println("14. Display all products");
        System.out.println("0. Exit");

        while (true) {
            int choice = -1;
            try {
                System.out.println();
                System.out.print("Enter your choice: ");

                //choice = sc.nextInt();
                //sc.nextLine();

                String input = sc.nextLine();
                choice = Integer.parseInt(input);
            }

            //catch (InputMismatchException e)

            catch (NumberFormatException  e) {
                System.out.println("Please enter a valid integer!");
                //sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    updateProduct(sc);
                    break;
                case 3:
                    searchByID(sc);
                    break;
                case 4:
                    deleteById(sc);
                    break;
                case 5:
                    getAllProductsByPriceDescending();
                    break;
                case 6:
                    getAllProductsByRangeDescending(sc);
                    break;
                case 7:
                    getAllProductsPriceAndManufacturingDateDescending();
                    break;
                case 8:
                    getAllProductsByName(sc);
                    break;
                case 9:
                    getAllProductsByManufacturingDate(sc);
                    break;
                case 10:
                    getAllProductsByPrice(sc);
                    break;
                case 11:
                    getAllProdutsWithNameCampanyPrice(sc);
                    break;
                case 12:
                    getTopKProductsByPrice(sc);
                    break;
                case 13:
                    getLeastKProductsByPrice(sc);
                    break;
                case 14:
                    getAllProducts();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }

    public static void addProduct(Scanner sc) {

        System.out.print("\nEnter product name: ");
        String name = sc.nextLine();

        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        LocalDate date = null;

        try {
            System.out.print("Enter manufacturing date (yyyy-MM-dd): ");
            date = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use yyyy-MM-dd.");
            return;
        }

        System.out.print("Enter quantity in hand: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter company name: ");
        String company = sc.nextLine();

        try {
            service.addProduct(name, price, date, quantity, company);
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateProduct(Scanner sc) {

        System.out.print("\nEnter Product ID you want to update: ");
        int productID = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter product name: ");
        String name = sc.nextLine();

        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter quantity in hand: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter company name: ");
        String company = sc.nextLine();

        try {
            Product product = service.updateProduct(productID, name, price, quantity, company);
            System.out.println("Product updated successfully!");
            System.out.println("Updated Product: ");
            displayProduct(product);
        } catch (
                InvalidProductPriceException |
                InvalidProductNameException |
                ProductNotFoundException |
                InvalidCompanyNameException |
                InvalidProductQuantityException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchByID(Scanner sc) {
        System.out.print("Enter product ID to search: ");
        int id = sc.nextInt();
        Product product = service.getProductByProductID(id);
        if (product != null) {
            displayProduct(product);
        } else {
            System.out.println("Product with ID " + id + " does not exist");
        }
    }

    public static void deleteById(Scanner sc) {
        System.out.print("Enter product ID to delete: ");
        int id = sc.nextInt();
        Product product = service.getProductByProductID(id);
        if (product != null) {
            service.deleteProductByProductId(id);
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product with ID " + id + " does not exist");
        }
    }

    public static void getAllProductsByPriceDescending() {
        System.out.println("Displaying all products: ");
        service.getProductsByPriceDescending()
                .forEach(Main::displayProduct);
    }

    public static void getAllProductsByRangeDescending(Scanner sc) {
        System.out.print("\nEnter lower price: ");
        double low = sc.nextDouble();
        System.out.print("Enter higher price: ");
        double high = sc.nextDouble();
        System.out.println("Displaying products in range " + low + " to " + high + ": ");
        service.getProductsInRangeByPriceDescending(low, high)
                .forEach(Main::displayProduct);
    }

    public static void getAllProductsPriceAndManufacturingDateDescending() {
        System.out.println("Displaying products: ");
        service.getProductsByPriceAndManufacturingDateDescending()
                .forEach(Main::displayProduct);
    }

    public static void getAllProductsByName(Scanner sc) {
        System.out.print("\nEnter product name to search similar products: ");
        String name = sc.nextLine();

        System.out.println("Displaying Prodcts with name " + name);
        try {
            service.getProductDetailsByProductName(name)
                    .forEach(Main::displayProduct);
        } catch (InvalidProductNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAllProductsByManufacturingDate(Scanner sc) {
        LocalDate date = null;

        try {
            System.out.println("\nEnter manufacturing date (yyyy-MM-dd): ");
            date = LocalDate.parse(sc.nextLine());
            System.out.println("Displaying all products with manufacturing date: " + date);
            service.getProductDetailsByManufacturingDate(date)
                    .forEach(Main::displayProduct);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use yyyy-MM-dd.");
            return;
        } catch (InvalidManufacturingDate e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAllProductsByPrice(Scanner sc) {
        System.out.print("\nEnter price to search products with the same price: ");
        double price = sc.nextDouble();

        try {
            System.out.println("Displaying products with similar price: ");
            service.getProductsByPrice(price)
                    .forEach(Main::displayProduct);
        } catch (InvalidProductPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAllProdutsWithNameCampanyPrice(Scanner sc) {
        System.out.print("\nEnter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter company name: ");
        String company = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        try {
            System.out.println("Displaying Products: ");
            service.getProductsByNameAndCompanyAndPrice(name, company, price)
                    .forEach(Main::displayProduct);
        } catch (
                InvalidProductNameException |
                InvalidCompanyNameException |
                InvalidProductPriceException e
        ) {
            System.out.println(e.getMessage());
        }
    }

    public static void getTopKProductsByPrice(Scanner sc) {
        System.out.print("\nEnter number of products to show: ");
        int k = sc.nextInt();

        try {
            System.out.println("Displaying top " + k + " products by price: ");
            service.getTopKProductsByPrice(k)
                    .forEach(Main::displayProduct);
        } catch (InvalidProductPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getLeastKProductsByPrice(Scanner sc) {
        System.out.print("\nEnter number of products to show: ");
        int k = sc.nextInt();

        try {
            System.out.println("Displaying least " + k + " products by price: ");
            service.getLeastKProductsByPrice(k)
                    .forEach(Main::displayProduct);
        } catch (InvalidProductPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAllProducts() {
        service.getAllProducts()
                .forEach(Main::displayProduct);
    }

    public static void displayProduct(Product product) {
        System.out.println(
                "\nID: " + product.getProductId() +
                "\nName: " + product.getProductName() +
                "\nPrice: " + product.getProductPrice() +
                "\nManufacturing Date: " + product.getManufacturingDate() +
                "\nQuantity: " + product.getQuantityInHand() +
                "\nCompany: " + product.getCompanyName()

        );
        System.out.println("-------------------------------");
    }
}