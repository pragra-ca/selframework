package io.pragra.framework.testcase;

import io.pragra.framework.conf.Configuration;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.listeners.ScreenShotListener;
import io.pragra.framework.pages.BlackTopNav;
import io.pragra.framework.pages.RequestDemoPage;
import io.pragra.framework.utils.CaptureTypeEnum;
import io.pragra.framework.utils.CoreUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Listeners(ScreenShotListener.class)
public class DemoTest {
    WebDriver driver;
    BlackTopNav topNav;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");
        //topNav = new BlackTopNav(driver);
    }

    @Test(enabled = false)
    public void verifyRequestDemo() {
        RequestDemoPage demoPage = topNav.clickRequestDemoLink();
        Assert.assertEquals(demoPage.getHeadingText(),"Request a Demo");
        demoPage.email("abc@gmail.com").company("ABC Corp").submit();
    }

    @Test(enabled = false)
    public void closeCookie() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.of(20, ChronoUnit.SECONDS));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("alert")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.dismiss();

    }

    @Test
    public void testName() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(300));

        //((JavascriptExecutor) driver).executeScript("setTimeout(function() {$('#demo').append('<button id=\"newElem\">Added My Atin</button>')},5000);");
        driver.findElement(By.id("populate-text")).click();
        Boolean until = wait.until(ExpectedConditions.textToBe(By.id("h2"), "Selenium Webdriver"));
        if(until) {
            System.out.println(driver.findElement(By.id("h2")).getText());
        }

        driver.findElement(By.id("display-other-button")).click();

        WebElement hidden = driver.findElement(By.id("hidden"));

        Assert.fail();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(hidden));
        element.click();



    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}

