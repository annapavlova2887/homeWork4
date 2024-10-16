package factory;
import exceptions.BrowserNotFoundException;
import factory.impl.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser");

    public WebDriver getDriver(String typeToOpen) {
        switch(browserName.toLowerCase()) {
            case "chrome": {
                return new ChromeDriver((ChromeOptions) new ChromeSettings().setting(typeToOpen));
            }
        }
        throw new BrowserNotFoundException(browserName);
    }
}
