import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationFormAutomation {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://proleed.academy/exercises/selenium/automation-practice-form-with-radio-button-check-boxes-and-drop-down.php");
        driver.manage().window().maximize();

        Select prefixDropdown = new Select(driver.findElement(By.id("prefix")));
        prefixDropdown.selectByValue("mr.");

        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        firstNameInput.sendKeys("Nagu");

        WebElement lastNameInput = driver.findElement(By.id("lastname"));
        lastNameInput.sendKeys("Chavala");

        WebElement savingsAccountType = driver.findElement(By.id("saving"));
        savingsAccountType.click();

        WebElement fatherNameInput = driver.findElement(By.id("fathername"));
        fatherNameInput.sendKeys("Srinu Chavala");

        WebElement motherNameInput = driver.findElement(By.id("mothername"));
        motherNameInput.sendKeys("Saraswathi Chavala");

        WebElement studentIdCheckbox = driver.findElement(By.id("studentid"));
        if (!studentIdCheckbox.isSelected()) {
            studentIdCheckbox.click();
        }

        WebElement passportCheckbox = driver.findElement(By.id("passport"));
        if (!passportCheckbox.isSelected()) {
            passportCheckbox.click();
        }

        WebElement idNumberInput = driver.findElement(By.id("identity_number"));
        idNumberInput.sendKeys("23A81A0513");

        WebElement maleGenderRadio = driver.findElement(By.id("male"));
        maleGenderRadio.click();

        Select month = new Select(driver.findElement(By.id("dob_month")));
        month.selectByVisibleText("August");

        Select date = new Select(driver.findElement(By.id("dob_date")));
        date.selectByVisibleText("30");

        Select year = new Select(driver.findElement(By.id("dob_year")));
        year.selectByVisibleText("2006");

        WebElement singleRadio = driver.findElement(By.id("single"));
        singleRadio.click();

        Select countryCode = new Select(driver.findElement(By.id("country_code")));
        countryCode.selectByValue("91");

        WebElement mobileInput = driver.findElement(By.id("mobile"));
        mobileInput.sendKeys("8106676329");

        Select nationality = new Select(driver.findElement(By.id("nationality")));
        nationality.selectByVisibleText("Indian");

        WebElement addressInput = driver.findElement(By.id("address"));
        addressInput.sendKeys("Jaggannapeta");
        WebElement stateInput = driver.findElement(By.id("state"));
        stateInput.sendKeys("Andhra Pradesh");

        Select country = new Select(driver.findElement(By.id("country")));
        country.selectByValue("India");

        WebElement submitButton = driver.findElement(By.xpath("(//div[@class = 'col-sm-4'])[7]/input[@value='Submit']"));
        submitButton.click();
    }
}
