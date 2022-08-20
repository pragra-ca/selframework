package io.pragra.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlackTopNav {
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='black-topbar']//ul/li[1]/a")
    private WebElement requestADemoLink;
    @FindBy(xpath = "//div[@id='black-topbar']//ul/li[2]/a")
    private WebElement one888Link;
    @FindBy(xpath = "//div[@id='black-topbar']//ul/li[2]/a")
    private WebElement support;

    public BlackTopNav(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public RequestDemoPage clickRequestDemoLink(){
        this.requestADemoLink.click();;
        return new RequestDemoPage(driver);
    }
}
