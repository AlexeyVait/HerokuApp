import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class FileUploadTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void fileUpload() {
        File file = new File("src/test/resources/5.jpg");
        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();
        String nameOfUploadedFile = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals(nameOfUploadedFile, "5.jpg", "Name of file in the page is not equal to the name of uploaded file");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
