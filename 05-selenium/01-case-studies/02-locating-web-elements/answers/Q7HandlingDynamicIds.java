import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q7HandlingDynamicIds {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Locate the dynamic username field using its stable name attribute.");
        WebElement dynamicUsername = driver.findElement(By.xpath("//input[@name = 'dynamicUsername']"));
        System.out.println("Located successfully!");

        System.out.println("\n2. Locate the dynamic email field using its stable data-testid.");
        WebElement dynamicEmail = driver.findElement(By.xpath("//input[@data-testid = 'dynamic-email']"));
        System.out.println("Located successfully!");

        System.out.println("\n3. Locate the dynamic password field using its class and type attributes.");
        WebElement dynamicPassword = driver.findElement(By.xpath("//input[@class = 'dynamic-field' and @type = 'password']"));
        System.out.println("Located successfully!");

        System.out.println("\n4. Locate the dynamic username field using starts-with() on its ID.");
        WebElement dynamicUsername2 = driver.findElement(By.xpath("//input[starts-with(@id, 'dynamic-username')]"));
        System.out.println("Located successfully!");

        System.out.println("\n5. Locate all three dynamic fields using their common class.");
        List<WebElement> allDynamicFields = driver.findElements(By.xpath("//input[@class = 'dynamic-field']"));
        System.out.println("Total elements located: " + allDynamicFields.size());

        System.out.println("\n6. Write a locator that identifies the dynamic email field without depending on its changing ID.");
        WebElement dynamicEmail2 = driver.findElement(By.xpath("//input[@name = 'dynamicEmail']"));
        System.out.println("Located successfully!");

    }
}
