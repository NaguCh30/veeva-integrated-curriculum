import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q1BasicLocators {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Locate the page header using its ID.");
        WebElement element = driver.findElement(By.id("page-header"));
        System.out.println("Page Header Located.");

        System.out.println("\n2. Locate the login username field using its ID.");
        WebElement loginUsername = driver.findElement(By.id("login-username"));
        System.out.println("Login Username located using id().");

        System.out.println("\n3. Locate the registration email field using its name.");
        WebElement registrationEmail = driver.findElement(By.name("email"));
        System.out.println("Registration Email Field located using name().");

        System.out.println("\n4. Locate the login password field using its name.");
        WebElement loginPassword = driver.findElement(By.name("password"));
        System.out.println("Login Password Located using name()");

        System.out.println("\n5. Locate the primary action button using its class name.");
        WebElement primaryActionButton = driver.findElement(By.className("primary-action"));
        System.out.println("Primary Action Button Located using className()");

        System.out.println("\n6. Locate all paragraph elements using their tag name.");
        List<WebElement> pElements = driver.findElements(By.tagName("p"));
        System.out.println("P elements located using tagName(). Total p elements: " + pElements.size());

        try {
            System.out.println("\n7. Locate the help link using its complete visible text.");
            WebElement helpLink = driver.findElement(By.linkText("Help"));
        } catch (Exception e) {
            System.out.println("Element not found");
        }

        try {
            System.out.println("\n8. Locate the help link using only the partial text Dynamic.");
            WebElement helpLinkPartial = driver.findElement(By.partialLinkText("hel"));
            System.out.println("Element located.");
        } catch (Exception e) {
            System.out.println("Element not found");
        }

        System.out.println("\n9. Locate the registration city dropdown using its ID.");
        WebElement cityDropDown = driver.findElement(By.id("registration-city"));
        System.out.println("City Drop Down Located using id()");

        System.out.println("\n10. Locate the summary button using its ID.");
        WebElement summaryButton = driver.findElement(By.id("summary-button"));
        System.out.println("Summary Button Located using id()");
    }
}
