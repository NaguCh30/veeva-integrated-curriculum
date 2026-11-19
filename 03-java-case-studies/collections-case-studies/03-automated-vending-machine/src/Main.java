import dummydata.DummyDataLoader;
import model.PaymentMethod;
import model.Product;
import model.Purchase;
import model.VendingMachine;
import repository.ProductRepository;
import service.VendingMachineService;
import utils.Utility;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        ProductRepository productRepository = new ProductRepository();
        VendingMachine vendingMachine = new VendingMachine();

        VendingMachineService service =
                new VendingMachineService(productRepository, vendingMachine);

        DummyDataLoader.load(service);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\nVENDING MACHINE APPLICATION");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");

            String input = readInput(scanner, "Enter your choice: ");

            if (input == null) continue;

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    adminMenu(scanner, service);
                    break;

                case 2:
                    userMenu(scanner, service);
                    break;

                case 3:
                    System.out.println("Thank you for using the Vending Machine!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // ==================== ADMIN MENU ====================

    private static void adminMenu(Scanner scanner, VendingMachineService service) {

        while (true) {

            Utility.displayAdminMenu();

            String input = readInput(scanner, "Enter your choice: ");

            if (input == null) return;

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    addProduct(scanner, service);
                    break;

                case 2:
                    showProductDetails(scanner, service);
                    break;

                case 3:
                    Utility.displayInventory(service);
                    break;

                case 4:
                    updateProduct(scanner, service);
                    break;

                case 5:
                    removeProduct(scanner, service);
                    break;

                case 6:
                    loadCash(scanner, service);
                    break;

                case 7:
                    showCashDenominationInventory(service);
                    break;

                case 8:
                    showCashBalance(service);
                    break;

                case 9:
                    showUpiBalance(service);
                    break;

                case 10:
                    showTotalBalance(service);
                    break;

                case 11:
                    collectCash(service);
                    break;

                case 12:
                    collectUpi(service);
                    break;

                case 13:
                    collectTotalMoney(service);
                    break;

                case 14:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ==================== ADMIN OPERATIONS ====================

    private static void addProduct(Scanner scanner, VendingMachineService service) {

        System.out.println("\nADD PRODUCT");

        Integer id = readIntegerInput(scanner, "Enter Product ID: ");
        if (id == null) return;

        String name = readInput(scanner, "Enter Product Name: ");
        if (name == null) return;

        Double price = readDoubleInput(scanner, "Enter Product Price: ");
        if (price == null) return;

        Integer quantity = readIntegerInput(scanner, "Enter Available Quantity: ");
        if (quantity == null) return;

        try {
            service.addProduct(id, name, price, quantity);

            System.out.println("Product added successfully!");
            Utility.displayProductDetails(service.getProductById(id));

        } catch (Exception e) {
            System.out.println("Unable to add product: " + e.getMessage());
        }
    }


    private static void updateProduct(Scanner scanner, VendingMachineService service) {

        System.out.println("\nUPDATE PRODUCT");

        Integer id = readIntegerInput(scanner, "Enter Product ID: ");
        if (id == null) return;

        try {
            Product product = service.getProductById(id);
            Utility.displayProductDetails(product);

            String name = readInput(scanner, "Enter New Product Name: ");
            if (name == null) return;

            Double price = readDoubleInput(scanner, "Enter New Price: ");
            if (price == null) return;

            Integer quantity = readIntegerInput(scanner, "Enter New Available Quantity: ");
            if (quantity == null) return;

            service.updateProductById(id, name, price, quantity);

            System.out.println("Product updated successfully!");

            Utility.displayProductDetails(service.getProductById(id));

        } catch (Exception e) {
            System.out.println("Unable to update product: " + e.getMessage());
        }
    }


    private static void removeProduct(Scanner scanner, VendingMachineService service) {

        System.out.println("\nREMOVE PRODUCT");

        Integer id = readIntegerInput(scanner, "Enter Product ID: ");
        if (id == null) return;

        try {
            Product product = service.getProductById(id);
            Utility.displayProductDetails(product);

            String confirmation = readInput(scanner,
                    "Confirm removal (YES/NO): ");

            if (confirmation == null) return;

            if (confirmation.equalsIgnoreCase("YES")) {

                service.removeProductById(id);
                System.out.println("Product removed successfully!");

            } else if (confirmation.equalsIgnoreCase("NO")) {

                System.out.println("Product removal cancelled.");

            } else {
                System.out.println("Invalid confirmation.");
            }

        } catch (Exception e) {
            System.out.println("Unable to remove product: " + e.getMessage());
        }
    }


    private static void loadCash(Scanner scanner, VendingMachineService service) {

        System.out.println("\nLOAD CASH INTO MACHINE");

        Map<Integer, Integer> cashToAdd = new LinkedHashMap<>();

        List<Integer> denominations =
                new ArrayList<>(service.getAvailableDenominations().keySet());

        denominations.sort(Comparator.reverseOrder());

        for (int denomination : denominations) {

            Integer count = readIntegerInput(scanner,
                    "Number of Rs. " + denomination + " notes/coins to add: ");

            if (count == null) return;

            if (count < 0) {
                System.out.println("Count cannot be negative.");
                return;
            }

            if (count > 0) {
                cashToAdd.put(denomination, count);
            }
        }

        if (cashToAdd.isEmpty()) {
            System.out.println("No cash was added.");
            return;
        }

        try {
            service.addCashInventory(cashToAdd);
            System.out.println("Cash loaded successfully!");

            showCashDenominationInventory(service);
            showCashBalance(service);

        } catch (Exception e) {
            System.out.println("Unable to load cash: " + e.getMessage());
        }
    }


    private static void showCashDenominationInventory(VendingMachineService service) {

        Map<Integer, Integer> inventory = service.getAvailableDenominations();

        List<Integer> denominations = new ArrayList<>(inventory.keySet());
        denominations.sort(Comparator.reverseOrder());

        System.out.println("\nCASH DENOMINATION INVENTORY");
        System.out.printf("%-18s %-10s%n", "Denomination", "Quantity");

        for (int denomination : denominations) {
            System.out.printf("Rs. %-15d %-10d%n",
                    denomination, inventory.get(denomination));
        }
    }


    private static void showCashBalance(VendingMachineService service) {

        System.out.println("\nCASH BALANCE");

        System.out.printf("Available Cash: Rs. %.2f%n",
                service.getAvailableCashMoney());
    }


    private static void showUpiBalance(VendingMachineService service) {

        System.out.println("\nUPI BALANCE");

        System.out.printf("Available UPI Money: Rs. %.2f%n",
                service.getAvailableUpiMoney());
    }


    private static void showTotalBalance(VendingMachineService service) {

        System.out.println("\nMACHINE BALANCE");

        System.out.printf("Cash Balance : Rs. %.2f%n",
                service.getAvailableCashMoney());

        System.out.printf("UPI Balance  : Rs. %.2f%n",
                service.getAvailableUpiMoney());

        System.out.printf("Total Balance: Rs. %.2f%n",
                service.getTotalAvailableMoney());
    }


    private static void collectCash(VendingMachineService service) {

        System.out.println("\nCOLLECT CASH");

        service.collectCashMoney();

        System.out.println("Cash collection completed.");
        showCashBalance(service);
    }


    private static void collectUpi(VendingMachineService service) {

        System.out.println("\nCOLLECT UPI MONEY");

        service.collectUpiMoney();

        System.out.println("UPI collection completed.");
        showUpiBalance(service);
    }


    private static void collectTotalMoney(VendingMachineService service) {

        System.out.println("\nCOLLECT TOTAL MONEY");

        service.collectTotalMoney();

        System.out.println("Total money collection completed.");
        showTotalBalance(service);
    }


    // ==================== USER MENU ====================

    private static void userMenu(Scanner scanner, VendingMachineService service) {

        while (true) {

            Utility.displayUserMenu();

            String input = readInput(scanner, "Enter your choice: ");

            if (input == null) return;

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    Utility.displayInventory(service);
                    break;

                case 2:
                    showProductDetails(scanner, service);
                    break;

                case 3:
                    Utility.displayAvailableCashDenominations(service);
                    break;

                case 4:
                    try {
                        purchaseProducts(scanner, service);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void showProductDetails(Scanner scanner,
                                           VendingMachineService service) {

        Integer productId = readIntegerInput(scanner, "Enter Product ID: ");

        if (productId == null) return;

        try {
            Product product = service.getProductById(productId);
            Utility.displayProductDetails(product);

        } catch (Exception e) {
            System.out.println("Unable to retrieve product: " + e.getMessage());
        }
    }


    // ==================== PURCHASE OPERATIONS ====================

    private static void purchaseProducts(Scanner scanner,
                                         VendingMachineService service) throws Exception {

        System.out.println("\nPRODUCT SELECTION");

        Utility.displayInventory(service);

        Map<Integer, Integer> selectedProducts =
                selectProducts(scanner, service);

        if (selectedProducts == null) return;

        printOrderSummary(service, selectedProducts);

        PaymentMethod paymentMethod = readPaymentMethod(scanner);

        if (paymentMethod == null) return;

        PaymentDetails payment = (paymentMethod == PaymentMethod.CASH)
                ? collectCashPayment(scanner, service)
                : collectUpiPayment(scanner);

        if (payment == null) return;

        System.out.printf("%nTotal Amount Inserted: Rs. %.2f%n",
                payment.amountPaid);

        try {
            Purchase purchase = service.purchaseProducts(
                    selectedProducts,
                    paymentMethod,
                    payment.amountPaid,
                    payment.denominations
            );

            printReceipt(purchase);

            System.out.println("\nUpdated Inventory:");
            Utility.displayInventory(service);

        } catch (Exception e) {
            System.out.println("Purchase failed: " + e.getMessage());
        }
    }


    private static Map<Integer, Integer> selectProducts(
            Scanner scanner, VendingMachineService service) {

        Map<Integer, Integer> selectedProducts = new LinkedHashMap<>();

        while (true) {

            String input = readInput(scanner,
                    "Enter Product ID (0 to finish, CANCEL to cancel): ");

            if (input == null) {
                System.out.println("Purchase cancelled.");
                return null;
            }

            int productId;

            try {
                productId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Product ID.");
                continue;
            }

            if (productId == 0) {

                if (selectedProducts.isEmpty()) {
                    System.out.println("Please select at least one product.");
                    continue;
                }

                return selectedProducts;
            }

            try {
                Product product = service.getProductById(productId);

                Utility.displayProductDetails(product);

                Integer quantity = readIntegerInput(scanner, "Enter quantity: ");

                if (quantity == null) {
                    System.out.println("Purchase cancelled.");
                    return null;
                }

                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than zero.");
                    continue;
                }

                selectedProducts.merge(productId, quantity, Integer::sum);

                System.out.println("Product added to selection.");

            } catch (Exception e) {
                System.out.println("Unable to select product: " + e.getMessage());
            }
        }
    }


    private static double printOrderSummary(
            VendingMachineService service,
            Map<Integer, Integer> selectedProducts) throws Exception {

        double totalCost = 0.0;

        System.out.println("\nORDER SUMMARY");
        System.out.printf("%-8s %-20s %-10s %-10s%n",
                "ID", "Product", "Quantity", "Subtotal");

        for (Map.Entry<Integer, Integer> entry : selectedProducts.entrySet()) {

            Product product = service.getProductById(entry.getKey());

            int quantity = entry.getValue();
            double subtotal = product.getProductPrice() * quantity;

            totalCost += subtotal;

            System.out.printf("%-8d %-20s %-10d Rs. %.2f%n",
                    product.getProductId(),
                    product.getProductName(),
                    quantity,
                    subtotal);
        }

        System.out.printf("%nTOTAL AMOUNT: Rs. %.2f%n", totalCost);

        return totalCost;
    }


    private static PaymentMethod readPaymentMethod(Scanner scanner) {

        String input = readInput(scanner, "\nSelect Payment Method (CASH / UPI): ");

        if (input == null) {
            System.out.println("Purchase cancelled.");
            return null;
        }

        try {
            return PaymentMethod.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment method.");
            return null;
        }
    }


    private static PaymentDetails collectCashPayment(
            Scanner scanner, VendingMachineService service) {

        System.out.println("\nCASH PAYMENT");

        double amountPaid = 0.0;

        Map<Integer, Integer> denominations = new LinkedHashMap<>();

        List<Integer> acceptedDenominations =
                new ArrayList<>(service.getAvailableDenominations().keySet());

        acceptedDenominations.sort(Comparator.reverseOrder());

        for (int denomination : acceptedDenominations) {

            Integer count = readIntegerInput(scanner,
                    "Enter number of Rs. " + denomination + " notes/coins: ");

            if (count == null) {
                System.out.println("Purchase cancelled.");
                return null;
            }

            if (count < 0) {
                System.out.println("Count cannot be negative.");
                return null;
            }

            if (count > 0) {
                denominations.put(denomination, count);
                amountPaid += (double) denomination * count;
            }
        }

        return new PaymentDetails(amountPaid, denominations);
    }


    private static PaymentDetails collectUpiPayment(Scanner scanner) {

        System.out.println("\nUPI PAYMENT");

        Double amountPaid = readDoubleInput(scanner, "Enter UPI payment amount: ");

        if (amountPaid == null) return null;

        if (amountPaid <= 0) {
            System.out.println("Payment amount must be greater than zero.");
            return null;
        }

        return new PaymentDetails(amountPaid, new LinkedHashMap<>());
    }


    private static void printReceipt(Purchase purchase) {

        System.out.println("\nPURCHASE SUCCESSFUL");

        System.out.println("Selected Products:");

        for (Map.Entry<Integer, Integer> entry :
                purchase.getSelectedProducts().entrySet()) {

            System.out.println("Product ID: " + entry.getKey()
                    + " | Quantity: " + entry.getValue());
        }

        System.out.printf("Total Cost     : Rs. %.2f%n", purchase.getTotalCost());
        System.out.println("Payment Method : " + purchase.getPaymentMethod());
        System.out.printf("Amount Paid    : Rs. %.2f%n", purchase.getAmountPaid());
        System.out.printf("Change Returned: Rs. %.2f%n", purchase.getChangeReturned());
    }


    // ==================== INPUT UTILITIES ====================

    private static String readInput(Scanner scanner, String prompt) {

        System.out.print(prompt);

        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("CANCEL")) {
            return null;
        }

        return input;
    }


    private static Integer readIntegerInput(Scanner scanner, String prompt) {

        String input = readInput(scanner, prompt);

        if (input == null) {
            System.out.println("Operation cancelled.");
            return null;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer.");
            return null;
        }
    }


    private static Double readDoubleInput(Scanner scanner, String prompt) {

        String input = readInput(scanner, prompt);

        if (input == null) {
            System.out.println("Operation cancelled.");
            return null;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
            return null;
        }
    }


    // ==================== PAYMENT DETAILS ====================

    private static class PaymentDetails {

        private final double amountPaid;
        private final Map<Integer, Integer> denominations;

        PaymentDetails(double amountPaid, Map<Integer, Integer> denominations) {
            this.amountPaid = amountPaid;
            this.denominations = denominations;
        }
    }
}
