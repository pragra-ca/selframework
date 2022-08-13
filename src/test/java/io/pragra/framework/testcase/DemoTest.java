package io.pragra.framework.testcase;

import io.pragra.framework.conf.Configuration;
import io.pragra.framework.drivermanager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    public void testConfig(){
        Assert.assertEquals(Configuration.getProperty("broswer.name"),"chrome");
    }

    @Test
    public void testZoom() throws InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        driver.get(Configuration.getProperty("app.url"));
        Thread.sleep(10000);
        driver.quit();
    }
}
