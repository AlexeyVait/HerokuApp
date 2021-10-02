import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContextMenuTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/context_menu");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void validateAlertText() {
        Actions actions = new Actions(driver);
        actions
                .contextClick(driver.findElement(By.id("hot-spot")))
                .build()
                .perform();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals(text, "You selected a context menu", "Alerts text is wrong");
        alert.dismiss();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
