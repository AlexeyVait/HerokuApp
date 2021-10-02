import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DragndropTest {

    public WebDriver driver;
    public Actions actions;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @Test
    public void changePlaces() {
        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = driver.findElement(By.id("column-b"));
        Action dragAndDrop = actions.dragAndDrop(boxA, boxB).build();
        dragAndDrop.perform(); //todo: Why does it work incorrectly?
        String boxName = driver.findElement(By.xpath("//*[@id='column-a']/header")).getText();
        Assert.assertEquals(boxName, "B", "DND doesn't work correctly");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
