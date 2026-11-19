package utils;

import model.Product;
import service.VendingMachineService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Utility {

    public static void displayInventory(VendingMachineService service) {

        List<Product> products = service.getAllProducts();

        int totalBoxes = 12;
        int columns = 4;
        int cellWidth = 24;                 // characters between two '|' separators
        int contentWidth = cellWidth - 2;   // 1 space padding on each side

        // One shared border line: +------+------+------+------+
        String border = ("+" + "-".repeat(cellWidth)).repeat(columns) + "+";

        // Title bar the same width as the grid
        String titleText = " VENDING MACHINE INVENTORY ";
        int side = (border.length() - titleText.length()) / 2;
        String title = "=".repeat(side) + titleText
                + "=".repeat(border.length() - side - titleText.length());

        System.out.println(title);
        System.out.println(border);   // single top border for the whole grid

        for (int row = 0; row < totalBoxes / columns; row++) {

            for (int line = 0; line < 4; line++) {
                StringBuilder sb = new StringBuilder("|");

                for (int col = 0; col < columns; col++) {
                    int index = row * columns + col;
                    Product product = index < products.size() ? products.get(index) : null;

                    String text;
                    switch (line) {
                        case 0:
                            text = String.format("BOX-%02d", index + 1);
                            break;
                        case 1:
                            text = "Product: " + (product != null ? product.getProductName() : "EMPTY");
                            break;
                        case 2:
                            text = "Price   : " + (product != null
                                    ? String.format("Rs. %.2f", product.getProductPrice())
                                    : "-");
                            break;
                        default:
                            text = "Stock   : " + (product != null
                                    ? String.valueOf(product.getAvailableQuantity())
                                    : "-");
                            break;
                    }

                    sb.append(formatCell(text, contentWidth)).append("|");
                }
                System.out.println(sb);
            }

            // Border shared between this row and the next (or the bottom of the grid)
            System.out.println(border);
        }
    }

    // " text<padding> " -> exactly contentWidth + 2 characters, truncating if too long
    private static String formatCell(String text, int contentWidth) {
        if (text.length() > contentWidth) {
            text = text.substring(0, contentWidth - 3) + "...";
        }
        return " " + String.format("%-" + contentWidth + "s", text) + " ";
    }


    public static void displayAdminMenu() {
        System.out.println("==================== ADMIN PANEL ====================");
        System.out.println(" 1. Add Product");
        System.out.println(" 2. View Product Details");
        System.out.println(" 3. View All Products");
        System.out.println(" 4. Update Product");
        System.out.println(" 5. Remove Product");
        System.out.println(" 6. Load Cash into Machine");
        System.out.println(" 7. View Cash Denominations");
        System.out.println(" 8. View Cash Balance");
        System.out.println(" 9. View UPI Balance");
        System.out.println("10. View Total Balance");
        System.out.println("11. Collect Cash");
        System.out.println("12. Collect UPI Money");
        System.out.println("13. Collect Total Money");
        System.out.println("14. Back to Main Menu");
    }


    public static void displayUserMenu() {
        System.out.println("==================== USER PANEL ====================");
        System.out.println("1. View All Products");
        System.out.println("2. View Product Details");
        System.out.println("3. View Available Cash Denominations");
        System.out.println("4. Purchase Products");
        System.out.println("5. Back to Main Menu");
    }


    public static void displayProductDetails(Product product) {

        System.out.println("\n==================== PRODUCT DETAILS ====================");
        System.out.println("Product ID   : " + product.getProductId());
        System.out.println("Product Name : " + product.getProductName());
        System.out.printf("Product Price: Rs. %.2f%n", product.getProductPrice());
        System.out.println("Available Stock: " + product.getAvailableQuantity());
        System.out.println();
    }

    public static void displayAvailableCashDenominations(VendingMachineService service) {

        List<Integer> denominations =
                new ArrayList<>(service.getAvailableDenominations().keySet());

        denominations.sort(Comparator.reverseOrder());

        System.out.println("\nACCEPTED CASH DENOMINATIONS");
        System.out.printf("%-20s%n", "Denomination");

        for (int denomination : denominations) {
            System.out.printf("Rs. %d%n", denomination);
        }
    }
}
