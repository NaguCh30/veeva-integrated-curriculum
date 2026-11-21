import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q2HandlingDuplicateAttributesAndMultipleElements {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Locate all elements having the class message.");
        List<WebElement> messageClassElements = driver.findElements(By.className("message"));
        System.out.println("All elements with message class are located. Total elements: " + messageClassElements.size());

        System.out.println("\n2. Locate all elements having the class form-control.\n");
        List<WebElement> formControlClassElements = driver.findElements(By.className("form-control"));
        System.out.println("All elements with form-control class are located. Total elements: " + formControlClassElements.size());

        System.out.println("\n3. Locate all buttons having the class action-button.");
        List<WebElement> actionButtonClassElements = driver.findElements(By.className("action-button"));
        System.out.println("All elements with action-button class are located. Total elements: " + actionButtonClassElements.size());

        System.out.println("\n4. Locate all product cards using their common class");
        List<WebElement> productCards = driver.findElements(By.className("product-card"));
        System.out.println("All elements with class product-card are located. Total elements: " + productCards.size());

        System.out.println("\n5. Locate all Buy buttons across the product cards.");
        List<WebElement> buyButtons = driver.findElements(By.className("buy-button"));
        System.out.println("All buy buttons are located. Total elements: " + buyButtons.size());

        System.out.println("\n6. Locate all Details buttons across the product cards.");
        List<WebElement> detailsButtons = driver.findElements(By.className("details-button"));
        System.out.println("All details buttons are located. Total elements: " + detailsButtons.size());

        System.out.println("\n7. Locate all employee cards.");
        List<WebElement> employeeCards = driver.findElements(By.className("employee-card"));
        System.out.println("All employee cards are located. Total elements: " + employeeCards.size());

        System.out.println("\n8. Locate all radio buttons belonging to the account-type group.");
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("[name= 'account-type'][type = 'radio']"));
        System.out.println("All radio buttons belonging to the account-type are selected. Total elements: " + radioButtons.size());

        System.out.println("\n9. Locate all checkboxes belonging to the interests group.");
        List<WebElement> interestsCheckBoxes = driver.findElements(By.cssSelector("[name = 'interest'][type='checkbox']"));
        System.out.println("All checkboxes in interests group are selected. Total elements: " + interestsCheckBoxes.size());

        System.out.println("\n10. Locate all checkboxes belonging to the product filters.");
        List<WebElement> productFiltersCheckBoxes = driver.findElements(By.cssSelector("[type='checkbox'][class='filter-option']"));
        System.out.println("All checkboxes in product filters are selected. Total elements: " + productFiltersCheckBoxes.size());

        System.out.println("\n11. Locate the first product card using findElements() and retrieve its first element.");
        List<WebElement> productCards2 = driver.findElements(By.className("product-card"));
        WebElement firstProductCard = productCards2.get(0);
        System.out.println("First product card selected.");

        System.out.println("\n12. Locate the fourth product card using a suitable locator strategy.");
        WebElement fourthProductCard = driver.findElement(By.xpath("//article[contains(concat(' ', normalize-space(@class), ' '), 'product-card')][4]"));
        System.out.println("4th product card located successfully using xpath");

    }
}
