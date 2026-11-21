import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q6AdvancedXpathAndPositionalSelection {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Locate all inputs whose type is text and whose class contains form-control.");
        List<WebElement> textAndFormControl = driver.findElements(By.xpath("//*[@type = 'text' and contains(@class, 'form-control')]"));
        System.out.println("Located all inputs with above requirements. Total elements: " + textAndFormControl.size());

        System.out.println("\n2. Locate inputs whose type is either email or tel.");
        List<WebElement> emailOrTel = driver.findElements(By.xpath("//input[@type = 'email' or @type = 'tel']"));
        System.out.println("Total elements located: " + emailOrTel.size());

        System.out.println("\n3. Locate all product cards that are featured and belong to the accessories category.");
        List<WebElement> featuredAccessories = driver.findElements(By.xpath("//article[contains(@class, 'featured') and @data-category = 'accessories']"));
        System.out.println("Total elements located: " + featuredAccessories.size());

        System.out.println("\n4. Locate all product cards that are not featured.");
        List<WebElement> notFeatured = driver.findElements(By.xpath("//article[contains(@class, 'product-card') and not(contains(@class, 'featured'))]"));
        System.out.println("Total elements located: " + notFeatured.size());

        System.out.println("\n5. Locate the first product card globally.");
        WebElement firstProductCard = driver.findElement(By.xpath("//*[contains(@class, 'product-card')][1]"));
        System.out.println("First product card located globally successfully.");

        System.out.println("\n6. Locate the third product card globally.\n");
        WebElement thirdProductCard = driver.findElement(By.xpath("//*[contains(@class, 'product-card')][3]"));
        System.out.println("Third product card located globally successfully.");

        System.out.println("\n7. Locate the last product card globally.\n");
        WebElement lastProductCard = driver.findElement(By.xpath("//*[contains(@class, 'product-card')][last()]"));
        System.out.println("Last product card located globally successfully.");

        System.out.println("\n8. Locate the first paragraph inside every employee card.");
        List<WebElement> firstPs = driver.findElements(By.xpath("//*[@class = 'employee-card']//p[1]"));
        System.out.println("Total elements located: " + firstPs.size());

        System.out.println("\n9. Locate the last paragraph inside every employee card.");
        List<WebElement> lastPs = driver.findElements(By.xpath("//*[@class = 'employee-card']//descendant::p[last()]"));
        System.out.println("Total elements located: " + lastPs.size());

        System.out.println("\n10. Locate the second product card's heading.");
        WebElement secondPHeading = driver.findElement(By.xpath("//*[contains(@class, 'product-card')][2]/h3"));
        System.out.println("Located the second product card heading successfully!");

        System.out.println("\n11. Locate the second product card's heading.");
        List<WebElement> firstTwoProductCards = driver.findElements(By.xpath("//*[contains(@class, 'product-card')][position() <= 2]"));
        System.out.println("Total elements located: " + firstTwoProductCards.size());

        System.out.println("\n12. Locate product cards at positions 2 and 4.");
        List<WebElement> twoProductCards = driver.findElements(By.xpath("//*[contains(@class, 'product-card')][position() = 2 or position() = 4]"));
        System.out.println("Total elements located: " + twoProductCards.size());

        System.out.println("\n13. Locate the product card that contains a paragraph with text Out of Stock");
        WebElement outOfStockProductCard = driver.findElement(By.xpath("//p[text() = 'Out of Stock']/parent::article[contains(@class, 'product-card')]"));
        System.out.println("Located successfully!");

        System.out.println("\n14. Locate the product card that contains a disabled Buy button.");
        WebElement disabledBuyProduct = driver.findElement(By.xpath("//*[contains(@class, 'product-card')]/descendant::button[@disabled]"));
        System.out.println("Located successfully!");

        System.out.println("\n15. Locate the employee card that contains the role Tester.");
        WebElement testerEmployeeCard = driver.findElement(By.xpath("//*[normalize-space() = 'Tester']/parent::*"));
        System.out.println("Located successfully!");
    }
}
