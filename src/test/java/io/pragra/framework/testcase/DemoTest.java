package io.pragra.framework.testcase;

import io.pragra.framework.conf.Configuration;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.pages.BlackTopNav;
import io.pragra.framework.pages.RequestDemoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DemoTest {
    WebDriver driver;
    BlackTopNav topNav;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");
        //topNav = new BlackTopNav(driver);
    }

    @Test(enabled = false)
    public void verifyRequestDemo() {
        RequestDemoPage demoPage = topNav.clickRequestDemoLink();
        Assert.assertEquals(demoPage.getHeadingText(),"Request a Demo");
        demoPage.email("abc@gmail.com").company("ABC Corp").submit();
    }

    @Test
    public void closeCookie() {
        ((JavascriptExecutor) driver).executeScript("setTimeout(function(){\n" +
                "    $('#enablecountdowntimer').append( \"<button id='custom-btn'>Test</button>\" );\n" +
                "},5000)");
        WebElement disable = driver.findElement(By.id("custom-btn"));
        disable.click();
        System.out.println(disable.getText());

    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}

