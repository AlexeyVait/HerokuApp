import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class AlertsTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void jsAlerts() {
        driver.findElement(By.cssSelector("[onclick = 'jsPrompt()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("asasa");
        alert.accept();
        driver.switchTo().defaultContent();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals(result, "You entered: asasa", "Entered text doesn't correspond to the displayed");
    }

    @Test
    public void digitAuth() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/digest_auth");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}