import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class DynamicControlTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkBoxShouldBeDisappeared() {
        //Найти чекбокс
        String checkboxText = driver.findElement(By.id("checkbox")).getText();
        assertEquals(checkboxText, "A checkbox", "Text is wrong or checkbox wasn't found");
        //Нажать на кнопку
        driver.findElement(By.cssSelector("[onclick='swapCheckbox()']")).click();
        //Дождаться надписи "It's gone"
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));
        //Проверить, что чекбокса нет
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[type='checkbox']")));
    }

    @Test
    public void inputShouldBeEnabled() {
        //Найти инпут
        driver.findElement(By.cssSelector("[type='text']"));
        //Проверить, что он disabled
        boolean inputFieldState = driver.findElement(By.cssSelector("[type='text']")).isEnabled();
        assertFalse(inputFieldState, "Input field is enabled");
        //Нажать на кнопку
        driver.findElement(By.cssSelector("[onclick='swapInput()']")).click();
        //Дождаться надписи “It's enabled!”
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's enabled!"));
        //Проверить, что инпут enabled
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='text']")));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
