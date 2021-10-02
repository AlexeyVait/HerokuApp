import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddRemoveElementTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void checkAmountOfElements() {
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        int numOfElements = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        Assert.assertEquals(numOfElements, 1, "Wrong amount");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
