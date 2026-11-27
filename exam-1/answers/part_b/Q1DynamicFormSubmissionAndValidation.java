package part_b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Q1DynamicFormSubmissionAndValidation {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.vikasqa.com/p/blog-page.html");
        System.out.println("Webpage opened.");

        WebElement username = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement((By.id("password")));
        WebElement radio = driver.findElement(By.xpath("//div[@class='radio-group']//input[@type='radio' and @value='Male']"));
        WebElement checkbox = driver.findElement(By.id("terms"));

        WebElement selectCountry = driver.findElement(By.id("country"));
        Select country = new Select(selectCountry);

        WebElement comments = driver.findElement(By.id("comments"));
        WebElement submitButton = driver.findElement(By.xpath("//button[text() = 'Submit']"));


        username.sendKeys("Nagu Chavala");
        email.sendKeys("naguch010@gmail.com");
        password.sendKeys("password123");

        if (!radio.isSelected()) {
            radio.click();
        } else {
            System.out.println("Already radio selected for male.");
        }

        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Checkbox marked now.");
        } else {
            System.out.println("Checkbox already marked.");
        }

        country.selectByValue("India");

        comments.sendKeys("I Love Veeva");

        Thread.sleep(3000);
        submitButton.click();

        driver.quit();

    }
}
