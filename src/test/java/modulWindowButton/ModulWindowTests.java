package modulWindowButton;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ModulWindowTests {

    private String baseurl = System.getProperty("base.url");
    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private String typeToOpen = "--start-fullscreen" ;

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    private String locatorModulButton = "openModalBtn";
    private String locatorModulWindow = "myModal";

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void initDriver() {
        driver = new WebDriverFactory().getDriver(typeToOpen);
        driver.get(baseurl);
    }

    @AfterEach
    public void close() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void modalWindow() {
        WebElement buttonForModalWindow = getElement(By.id(locatorModulButton));
        WebElement modulWindow = getElement(By.id(locatorModulWindow));
        wait.until(ExpectedConditions.visibilityOf(buttonForModalWindow));
        Assertions.assertFalse(modulWindow.isDisplayed());
        buttonForModalWindow.click();
        wait.until(ExpectedConditions.visibilityOf(modulWindow));
        Assertions.assertTrue(modulWindow.isDisplayed());
    }
}
