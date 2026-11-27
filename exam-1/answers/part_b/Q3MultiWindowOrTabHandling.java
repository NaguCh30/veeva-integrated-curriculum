package part_b;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class Q3MultiWindowOrTabHandling {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
        System.out.println("Webpage opened.");

        String parentUrl = driver.getCurrentUrl();
        System.out.println("Parent URL: " + parentUrl);

        String parentTitle = driver.getTitle();
        System.out.println("Parent Title: " + parentTitle);

        String parentId = driver.getWindowHandle();
        System.out.println("Parent ID: " + parentId);

        Thread.sleep(3000);

        WebElement newTabButton = driver.findElement(By.id("newTabBtn"));
        newTabButton.click();

        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentId)) {
                driver.switchTo().window(handle);
                System.out.println("Switch to child tab.");
                break;
            }
        }

        Thread.sleep(3000);

        String childUrl = driver.getCurrentUrl();
        System.out.println("\nChild URL: " + childUrl);

        String childTitle = driver.getTitle();
        System.out.println("Child Title: " + childTitle);

        String childId = driver.getWindowHandle();
        System.out.println("Child ID: " + childId);

        for (String handle : handles) {
            if (handle.equals(childId)) {
                driver.close();
                System.out.println("Child tab closed.");
                break;
            }
        }

        Thread.sleep(3000);

        driver.switchTo().window(parentId);

        System.out.println("\nSwitched back to the parent tab.");
        System.out.println("Cuurent Tab ID: " + driver.getWindowHandle());
    }
}
