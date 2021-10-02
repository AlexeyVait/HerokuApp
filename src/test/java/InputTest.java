import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class InputTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/inputs");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void inputType() {
        //WebElement input = driver.findElement(By.xpath("//*[@id='content']/div/div/div/input"));
        WebElement input = driver.findElement(By.tagName("input"));
        String typeValue = input.getAttribute("type");
        Assert.assertEquals(typeValue, "number", "Type of value is wrong");
    }

    @Test
    public void charactersShouldCauseError() {
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys("e"); //todo: implement assert; this case should be failed
    }

    @Test
    public void inputDigitsUsingKeyboard() {
        driver.findElement(By.tagName("input")).sendKeys("2");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        String value = driver.findElement(By.tagName("input")).getText();
        Assert.assertEquals(value, "2", "Wrong value");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
