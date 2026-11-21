import jdk.dynalink.linker.LinkerServices;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Q4XpathBasicExpressionsAndFunctions {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///D:/Nagu/Veeva/Practice/Selenium/locators/selenium_locator_practice_lab.html");
        driver.manage().window().maximize();
        System.out.println("Web Page Opened Successfully!");

        System.out.println("\n1. Locate the login button using its exact text.");
        WebElement loginButton = driver.findElement(By.xpath("//button[text() = 'Login']"));
        System.out.println("Login button located using text() and 'Login'");

        System.out.println("\n2. Locate the registration button using its exact text.");
        WebElement registrationButton = driver.findElement(By.xpath("//button[text() = 'Create Account']"));
        System.out.println("Registration button located using text() and 'Create Account'");

        System.out.println("\n3. Locate the product named Laptop Pro");
        WebElement laptopPro = driver.findElement(By.xpath("//article//h3[text() = 'Laptop Pro']"));
        System.out.println("Product with name Laptop Pro is selected.");

        System.out.println("\n4. Locate the product card having data-product-id='P103'.");
        WebElement P103 = driver.findElement(By.xpath("//article[@data-product-id='P103']"));
        System.out.println("Product with data-product-id = 'P103' is located.");

        System.out.println("\n5. Locate all product cards having data-category='accessories'.");
        List<WebElement> accessories = driver.findElements(By.xpath("//*[@class = 'product-card' and @data-category = 'accessories']"));
        System.out.println("All products with accessories category are selected. Total products: " + accessories.size());

        System.out.println("\n6. Locate all inputs having type='password'.");
        List<WebElement> passwordInputs = driver.findElements(By.xpath("//input[@type = 'password']"));
        System.out.println("All password inputs are selected. Total elements: " + passwordInputs.size());

        System.out.println("\n7. Locate all buttons whose text contains View.");
        List<WebElement> viewButtons = driver.findElements(By.xpath("//button[contains(text(), 'View')]"));
        System.out.println("All buttons which contains text 'View' are selected. Total elements: " + viewButtons.size());

        System.out.println("\n8. Locate all product names whose text contains Monitor.");
        List<WebElement> monitorProducts = driver.findElements(By.xpath("//h3[contains(text(), 'Monitor')]"));
        System.out.println("Located all elements which contains text 'Monitor'. Total elements: " + monitorProducts.size());

        System.out.println("\n9. Locate all elements whose class contains featured.");
        List<WebElement> featuredElements = driver.findElements(By.xpath(
                "//*[contains(concat(' ', normalize-space(@class), ' '), 'featured')]"
        ));
        System.out.println("Located all elements whose class contains featured. Total elements: " + featuredElements.size());

        System.out.println("\n10. Locate all inputs whose IDs start with dynamic-username.");
        List<WebElement> dynamicUsernameInputs = driver.findElements(By.xpath("//input[starts-with(@id, 'dynamic-username')]"));
        System.out.println("Located all inputs whose IDs starts with dynamic-username. Total elements: " + dynamicUsernameInputs.size());

        System.out.println("\n11. Locate all buttons whose text starts with Create.");
        List<WebElement> createButtons = driver.findElements(By.xpath("//button[starts-with(text(), 'Create')]"));
        System.out.println("Located all buttons whose text starts with 'Create'. Total elements: " + createButtons.size());

        System.out.println("\n12. Locate the paragraph whose normalized text is In Stock.");
        List<WebElement> pInStock = driver.findElements(By.xpath("//p[normalize-space() = 'In Stock']"));
        System.out.println("Located all the p elements whose normalized text is 'In Stock'. Total elements: " + pInStock.size());

        System.out.println("\n13. Locate the paragraph whose normalized text contains years experience.");
        List<WebElement> pHasYearsExperience = driver.findElements(By.xpath("//p[contains(normalize-space(), 'years experience')]"));
        System.out.println("Located all the elements whose normalized text contains 'years experience'. Total elements: " + pHasYearsExperience.size());

        System.out.println("\n14. Locate all product cards using robust whole-class-token matching for product-card.");
        List<WebElement> wholeClassToken = driver.findElements(By.xpath("//*[contains(concat(' ', normalize-space(@class), ' '), 'product-card')]"));
        System.out.println("Located all the elements whose whole class is matching for product-id. Total elements: " + wholeClassToken.size());

        System.out.println("\n15. Locate the button whose complete string value is Deep Action");
        WebElement deepActionButton = driver.findElement(By.xpath("//button[text() = 'Deep Action']"));
        System.out.println("Button with string value Deep Action is located.");
    }
}
