import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q3CssSelectors {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Select the element with ID welcome-message.");
        WebElement welcomeMessage = driver.findElement(By.cssSelector("#welcome-message"));
        System.out.println("Element with ID 'welcome-message is selected.");

        System.out.println("\n2. Select all elements with class product-card");
        List<WebElement> productCards = driver.findElements(By.cssSelector("[class = 'product-card']"));
        System.out.println("All elements with product-card class are selected. Total elements: " + productCards.size());

        System.out.println("\n3. Select all elements having both product-card and featured classes.");
        List<WebElement> productCardsFeatured = driver.findElements(By.cssSelector(".product-card.featured"));
        System.out.println("All elements having both product-card and featured classes are selected. Total elements: " + productCardsFeatured.size());

        System.out.println("\n 4. Select all buttons having the class buy-button.");
        List<WebElement> buyButtons = driver.findElements(By.cssSelector(".buy-button"));
        System.out.println("All elements with class buy-button are selected. Total elements: " + buyButtons.size());

        System.out.println("\n5. Select the product card whose data-product-id is P104.");
        WebElement productCard = driver.findElement(By.cssSelector("[data-product-id = 'P104']"));
        System.out.println("Product card with data-product-id P104 is selected.");

        System.out.println("\n6. Select all product cards whose data-category is electronics.");
        List<WebElement> productCards2 = driver.findElements(By.cssSelector("[data-category = 'electronics']"));
        System.out.println("All product cards with data-category electronics are selected. Total elements: " + productCards2.size());

        System.out.println("\n7. Select all inputs having type='password'");
        List<WebElement> passwordInputs = driver.findElements(By.cssSelector("[type='password']"));
        System.out.println("All elements with input type password are selected. Total elements: " + passwordInputs.size());

        System.out.println("\n8. Select all inputs having a data-testid attribute.");
        List<WebElement> dataTestIdElements = driver.findElements(By.cssSelector("[data-testid]"));
        System.out.println("All elements with attribute data-testid are selected. Total elements: " + dataTestIdElements.size());

        System.out.println("\n9. Select the registration username field using its data-testid.");
        WebElement registrationUsername = driver.findElement(By.cssSelector("[data-testid = 'registration-username']"));
        System.out.println("Registration username is selected with data-testid attribute.");

        System.out.println("\n10. Select all elements whose class contains product.");
        List<WebElement> containsProduct = driver.findElements(By.cssSelector("[class *= 'product']"));
        System.out.println("All elements with class contains product are selected. Total elements: " + containsProduct.size());

        System.out.println("\n11. Select the username and email fields inside the registration form.");
        List<WebElement> userNameAndEmail = driver.findElements(By.cssSelector("#registration-username, " + "#registration-email"));
        System.out.println("Both username and email are selected. Selected elements: " + userNameAndEmail.size());

        System.out.println("\n12. Select only direct article children of the product grid.");
        List<WebElement> directArticleChildren = driver.findElements(By.cssSelector(".product-grid > article"));
        System.out.println("All direct article children for product grid are selected. Total elements: " + directArticleChildren.size());

        System.out.println("\n13. Select all paragraphs that are descendants of employee cards.");
        List<WebElement> allDescendantPsOfEmployeeCards = driver.findElements(By.cssSelector(".employee-card p"));
        System.out.println("All descendant P for employee cards are selected. Total elements: " + allDescendantPsOfEmployeeCards.size());

        System.out.println("\n14. Select the first product card.");
        WebElement firstProductCard = driver.findElement(By.cssSelector(".product-card:first-child"));
        System.out.println("First product card is selected.");

        System.out.println("\n15. Select the last product card.");
        WebElement lastProductCard = driver.findElement(By.cssSelector(".product-card:last-child"));
        System.out.println("Last product card is selected.");

        System.out.println("\n16. Select all product cards except featured cards.");
        List<WebElement> exceptFeaturedProductCards = driver.findElements(By.cssSelector(".product-card:not(.featured)"));
        System.out.println("All product cards selected except featured product cards. Total elements: " + exceptFeaturedProductCards.size());

        System.out.println("\n17. Select all enabled Buy buttons.");
        List<WebElement> enabledByButtons = driver.findElements(By.cssSelector(".buy-button:enabled"));
        System.out.println("All enabled buy buttons are selected. Total elements: " + enabledByButtons.size());

        System.out.println("\n18. Select the disabled Buy button");
        List<WebElement> disabledByButtons = driver.findElements(By.cssSelector(".buy-button:disabled"));
        System.out.println("All disabled buy buttons are selected. Total elements: " + disabledByButtons.size());

        System.out.println("\n19. Select all checked checkboxes inside the product filters section.");
        List<WebElement> checkedCheckBoxes = driver.findElements(By.cssSelector("#filters-section [type = 'checkbox']:checked"));
        System.out.println("All checked check boxes are selected inside the filters section. Total elements: " + checkedCheckBoxes.size());

        System.out.println("\n20. Select the h3 element that is a direct child of the inner panel.");
        WebElement h3Element = driver.findElement(By.cssSelector(".inner-panel > h3"));
        System.out.println("h3 element which direct child of inner panel is selected.");

    }
}
