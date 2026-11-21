import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.List;

public class Q5XpathAxesAndDomRelationships {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Locate the parent of the Laptop Pro heading.");
        WebElement laptopProParent = driver.findElement(By.xpath("//h3[text() = 'Laptop Pro']/parent::*"));
        System.out.println("Laptop Pro heading parent is located.");

        System.out.println("\n2. Locate all direct children of the first product card.");
        List<WebElement> childrenFirstProduct = driver.findElements(By.xpath("//article[contains(@class, 'product-card')][1]/child::*"));
        System.out.println("All direct children of the first product-card are selected. Total elements: " + childrenFirstProduct.size());

        System.out.println("\n3. Locate all descendants of the first product card.");
        List<WebElement> descendantFirstProduct = driver.findElements(By.xpath("//article[contains(@class, 'product-card')][1]/descendant::*"));
        System.out.println("All descendants of the first product-card are selected. Total elements: " + descendantFirstProduct.size());

        System.out.println("\n4. Locate the employee card that contains the heading David.");
        WebElement employeeCard = driver.findElement(By.xpath("//h3[text() = 'David']/parent::*[@class = 'employee-card']"));
        System.out.println("Employee card containing heading David is located.");

        System.out.println("\n5. Locate the parent of the paragraph containing Tester.");
        WebElement testerPParent = driver.findElement(By.xpath("//p[text() = 'Tester']/parent::*"));
        System.out.println("Located the parent of the paragraph containing text 'Tester'.");

        System.out.println("\n6. Starting from the David heading, locate its following sibling paragraph.");
        List<WebElement> siblingPs = driver.findElements(By.xpath("//h3[text() = 'David']/following-sibling::p"));
        System.out.println("All p siblings from David heading are located. Total elements: " + siblingPs.size());

        System.out.println("\n7. Starting from the Tester paragraph, locate its preceding sibling heading");
        List<WebElement> precedingSiblingHeading = driver.findElements(By.xpath("//p[text() = 'Tester']/preceding-sibling::h3"));
        System.out.println("All preceding siblings of paragraph with text 'Tester' are located. Total elements: " + precedingSiblingHeading.size());

        System.out.println("\n8. Starting from the View Profile button inside John's card, locate the employee heading using a suitable relationship");
        WebElement johnHeading = driver.findElement(By.xpath("//*[@class='employee-card'][1]/button[@class = 'view-profile']/preceding-sibling::h3"));
        System.out.println("John heading located using view-profile button.");

        System.out.println("\n9. Starting from the Laptop Pro heading, locate the Buy button using a descendant relationship.");
        WebElement laptopProBuyButton = driver.findElement(By.xpath("//h3[text() = 'Laptop Pro']/ancestor::article/descendant::button[@data-action= 'buy']"));
        System.out.println("Laptop Pro buy button located.");

        System.out.println("\n10. Starting from the Deep Action button, locate the outer panel using an ancestor relationship.");
        WebElement deepActionOuterPanel = driver.findElement(By.xpath("//button[text() = 'Deep Action']/ancestor::div[@id = 'outer-panel']"));
        System.out.println("Deep Action outer panel located using ancestor relationship.");

        System.out.println("\n11. Starting from the Deep Panel heading, locate the paragraph that follows it as a sibling.");
        WebElement deepPanelParagraph = driver.findElement(By.xpath("//h3[text() = 'Deep Panel']/following-sibling::p"));
        System.out.println("Paragraph located from the Deep Panel heading.");

        System.out.println("\n12. Locate the Inner Panel heading using the self axis after identifying it by text.");
        WebElement innerPanelHeading = driver.findElement(By.xpath("//h3[text() = 'Inner Panel']/self::*"));
        System.out.println("Inner Panel heading is located using self axis.");

    }
}
