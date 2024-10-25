package factory;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

public class TestClass {
    public boolean testModul(WebElement element) {
        return element.isDisplayed();
    }
}
