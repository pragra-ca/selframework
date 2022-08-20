package io.pragra.framework.testcase;

import io.pragra.framework.conf.Configuration;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.pages.BlackTopNav;
import io.pragra.framework.pages.RequestDemoPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoTest {
    WebDriver driver;
    BlackTopNav topNav;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://zoom.us");
        topNav = new BlackTopNav(driver);
    }

    @Test
    public void verifyRequestDemo() {
        RequestDemoPage demoPage = topNav.clickRequestDemoLink();
        Assert.assertEquals(demoPage.getHeadingText(),"Request a Demo");
        demoPage.email("abc@gmail.com").company("ABC Corp").submit();
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}

