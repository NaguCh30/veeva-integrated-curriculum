package part_b;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Q2HandlingAlertsAndPopups {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
        System.out.println("Webpage opened.");

        WebElement alertBox = driver.findElement(By.id("alertBox"));
        WebElement confirmBox = driver.findElement(By.id("confirmBox"));
        WebElement promptBox = driver.findElement(By.id("promptBox"));

        alertBox.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Box Text: " + alertText);
        alert.accept();

        confirmBox.click();
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        System.out.println("Confirm Box Text: " + confirmText);
        confirm.dismiss();

        promptBox.click();
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("Nagu from Veeva");
        String promptText = prompt.getText();
        System.out.println("Prompt Box Text: " + promptText);
        prompt.accept();
    }
}
