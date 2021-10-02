import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropdownTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dropdown");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void availableElements() {
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> numOfElements = dropdown.getOptions();
        Assert.assertEquals(numOfElements.size(), 3, "Amount of options is incorrect");
    }

    @Test
    public void selectFirstOption() {
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");
        String text = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(text, "Option 1", "Chosen option is incorrect");
    }

    @Test
    public void selectSecondOption() {
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 2");
        String text = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(text, "Option 2", "Chosen option is incorrect");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
