import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q8LocatingElementsRelativeToAnotherElement {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. First locate the product card with ID P104. Then locate its product name relative to that card.");
        By p104 = By.xpath("//*[@data-product-id= 'P104']");
        WebElement product = driver.findElement(p104);
        WebElement productName = product.findElement(By.xpath("./h3"));
        System.out.println("Located successfully!");

        System.out.println("\n2. Locate the Buy button inside the Laptop Pro product card.");
        WebElement laptopProBuyButton = driver.findElement(By.xpath("//article[@data-product-id = 'P101']//button[@data-action = 'buy']"));
        System.out.println("Located successfully!");

        System.out.println("\n3. Locate the Details button inside the Smartphone X product card.");
        WebElement detailsButton = driver.findElement(By.cssSelector("[data-product-id = 'P102'] [data-action = 'details']"));
        System.out.println("Located successfully!");

        System.out.println("\n4. Locate the stock status inside the Mechanical Keyboard card.");
        WebElement stockStatus = driver.findElement(By.cssSelector("[data-product-id = 'P104'] > .product-stock"));
        System.out.println("Located successfully!");

        System.out.println("\n5. Locate the experience paragraph inside David's employee card.");
        WebElement experience = driver.findElement(By.cssSelector("[data-employee-id='E202'] .employee-experience"));
        System.out.println("Located successfully!");

        System.out.println("\n6. Locate the View Profile button inside John's employee card.");
        WebElement viewProfileJohn = driver.findElement(By.xpath("//*[@data-employee-id = 'E201']/button"));
        System.out.println("Located successfully!");

        System.out.println("\n7. Locate all paragraph elements inside the employee card belonging to Mike.");
        List<WebElement> allMikePs = driver.findElements(By.xpath("//*[@data-employee-id = 'E203']/descendant::p"));
        System.out.println("Total elements: " + allMikePs.size());

        System.out.println("\n8. Locate the password field inside the login form.");
        WebElement loginFormPasswordField = driver.findElement(By.cssSelector("#login-form [type = 'password']"));
        System.out.println("Login form password field located.");

        System.out.println("\n9. Locate all checkboxes inside the registration form.");
        List<WebElement> allRegistrationFormCheckboxes = driver.findElements(By.xpath("//section[@id = 'registration-section']/descendant::*[@type='checkbox']"));
        System.out.println("Total elements: " + allRegistrationFormCheckboxes.size());
    }
}
