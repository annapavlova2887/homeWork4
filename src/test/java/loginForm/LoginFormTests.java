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
        return driver.findElement(locator);
    }

    private String nameForTest = "фыв";
    private String emailForTest = "asdf@sdfg.rt";

    private String locatorFieldName = "name";
    private String locatorFieldEmail = "email";
    private String locatorSentButton = "[type=\"submit\"]";
    private String locatorMessageForm = "#messageBox";

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
        WebElement fieldName = getElement(By.id(locatorFieldName));
        WebElement fieldEmail = getElement(By.id(locatorFieldEmail));
        WebElement sentButton = getElement(By.cssSelector(locatorSentButton));
        WebElement messageForm = getElement(By.cssSelector(locatorMessageForm));
        wait.until(ExpectedConditions.visibilityOf(fieldName));
        fieldName.sendKeys(nameForTest);
        fieldEmail.sendKeys(emailForTest);
        sentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageForm));
        Assertions.assertEquals("Форма отправлена с именем: " + nameForTest + " и email: " + emailForTest, messageForm.getText());
    }
}
