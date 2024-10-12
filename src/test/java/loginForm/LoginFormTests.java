package loginForm;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginFormTests {

    private String baseurl = System.getProperty("base.url");
    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private String typeToOpen = "--start-maximized";

    private   WebElement getElement(By locator) {
        return driver.findElement(locator); // - уточни как лучше, стоит ли мучаться и выносить локаторы
    }

    private String nameForTest = "фыв";
    private String emailForTest = "asdf@sdfg.rt";

    private String locatorNameForm = "name";
    private String locatorEmailForm = "email";
    private String locatorSentButton = "[type=\"submit\"]";
    private String locatorMessForm = "#messageBox";

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
    public void loginForm() {
        WebElement nameForm = getElement(By.id(locatorNameForm));
        WebElement emailForm = getElement(By.id(locatorEmailForm));
        WebElement sentButton = getElement(By.cssSelector(locatorSentButton));
        WebElement messForm = getElement(By.cssSelector(locatorMessForm));
        wait.until(ExpectedConditions.visibilityOf(nameForm));
        nameForm.sendKeys(nameForTest);
        emailForm.sendKeys(emailForTest);
        sentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messForm));
        Assertions.assertEquals("Форма отправлена с именем: " + nameForTest + " и email: " + emailForTest, messForm.getText());
    }
}
