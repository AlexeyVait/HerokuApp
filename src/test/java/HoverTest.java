import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HoverTest {

    public WebDriver driver;
    public Actions action;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/hovers");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void profileUser1() {
        //hover the image to check the name
        action = new Actions(driver);
        WebElement user1 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/img"));
        action.moveToElement(user1).perform();
        String captionUser1 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/div/h5")).getText();
        Assert.assertEquals(captionUser1, "name: user1", "Name is wrong");

        //click on the link to check the error
        driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/div/a")).click();
        String error1 = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(error1, "Found", "404 error occurred");
    }

    @Test
    public void profileUser2() {
        //hover the image to check the name
        action = new Actions(driver);
        WebElement user2 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/img"));
        action.moveToElement(user2).perform();
        String captionUser2 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div/h5")).getText();
        Assert.assertEquals(captionUser2, "name: user2", "Name is wrong");

        driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div/a")).click();
        String error2 = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(error2, "Found", "404 error occurred");
    }

    @Test
    public void profileUser3() {
        action = new Actions(driver);
        WebElement user3 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[3]/img"));
        action.moveToElement(user3).perform();
        String captionUser3 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[3]/div/h5")).getText();
        Assert.assertEquals(captionUser3, "name: user3", "Name is wrong");

        driver.findElement(By.xpath("//*[@id='content']/div[1]/div[3]/div/a")).click();
        String error3 = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(error3, "Found", "404 error occurred");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
