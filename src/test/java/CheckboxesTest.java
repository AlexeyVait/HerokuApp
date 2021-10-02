import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckboxesTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void checkboxesSelection() {
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("[type='checkbox']"));
        assertFalse(checkBoxes.get(0).isSelected(), "First checkbox is not selected");
        checkBoxes.get(0).click();
        assertTrue(checkBoxes.get(0).isSelected(), "First checkbox is selected");
        assertTrue(checkBoxes.get(1).isSelected(), "Second checkbox is selected");
        checkBoxes.get(1).click();
        assertFalse(checkBoxes.get(1).isSelected(), "Second checkbox is not selected");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
