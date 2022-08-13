package io.pragra.framework.drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.pragra.framework.conf.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager instance;
    private DriverManager() {
        String browser = Configuration.getProperty("broswer.name");
        if(browser.equalsIgnoreCase(BrowserType.CHROME)){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(BrowserType.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase(BrowserType.SAFARI)) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }else {
            System.out.println("Setting up default to Chromne");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

    }

    public synchronized static WebDriver getDriver(){
        if(instance==null) {
            instance = new DriverManager();
        }
        return instance.driver;
    }
}
