package searchFiels;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchFieldTests {
    private String baseurl = System.getProperty("base.url");
    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private String typeToOpen = "--headless" ;

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    private String locatorOfFindWin = "textInput";

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
    public void textOtusTest() {
        WebElement findWindow = getElement(By.id(locatorOfFindWin));
        wait.until(ExpectedConditions.visibilityOf(findWindow));
        findWindow.clear();
        findWindow.sendKeys("ОТУС");
        Assertions.assertEquals("ОТУС", findWindow.getAttribute("value"));
    }
}