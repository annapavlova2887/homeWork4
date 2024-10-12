package factory.impl;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public  class ChromeSettings implements IWebDriverSettings {

    @Override
    public AbstractDriverOptions setting(String typeOpen) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(typeOpen); //"start-fullscreen"
        return chromeOptions;
    }

}
