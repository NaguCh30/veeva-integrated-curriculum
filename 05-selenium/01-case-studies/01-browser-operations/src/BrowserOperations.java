import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class BrowserOperations {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        String pageSource = driver.getPageSource();
        System.out.println("\nPage Source Length: " + pageSource.length());

        System.out.println("\nNavigating to 'Dynamic Loading' page");
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading");
        System.out.println("Navigating backward...");
        driver.navigate().back();
        System.out.println("Navigating forward...");
        driver.navigate().forward();
        System.out.println("Refreshing current page...");
        driver.navigate().refresh();

        Dimension dimension = driver.manage().window().getSize();
        System.out.println("\nDimensions: \nWidth = " + dimension.getWidth() + "\nHeight = " + dimension.getHeight());

        System.out.println("\nResizing browser");
        driver.manage().window().setSize(new Dimension(1000, 700));
        Dimension updatedDimension = driver.manage().window().getSize();
        System.out.println("Updated dimensions: \nWidth: " + updatedDimension.getWidth() + "\nHeight: " + updatedDimension.getHeight());

        System.out.println("\nChanging browser position: ");
        driver.manage().window().setPosition(new Point(100, 100));
        Point updatedPosition = driver.manage().window().getPosition();
        System.out.println("Updated Position: \nX = " + updatedPosition.getX() + "\nY = " + updatedPosition.getY());

        System.out.println("\nMinimizing the browser...");
        driver.manage().window().minimize();

        System.out.printf("Retrieving the current page title after minimizing: ");
        System.out.println("Current Page Title: " + driver.getTitle());

        System.out.println("\nSetting browser to Full Screen");
        driver.manage().window().fullscreen();

        System.out.println("\nTerminating the session");
        driver.quit();
    }
}