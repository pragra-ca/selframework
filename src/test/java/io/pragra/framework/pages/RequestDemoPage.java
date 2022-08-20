package io.pragra.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestDemoPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='support_contact']//h2[@class='fromNormal']")
    private WebElement heading;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "btnSubmit")
    private WebElement submit;


    public RequestDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getHeadingText() {
        return this.heading.getText();
    }

    public RequestDemoPage email(String email) {
        this.email.sendKeys(email);
        return this;
    }
    public RequestDemoPage company(String company) {
        this.company.sendKeys(company);
        return this;
    }

    public void submit() {
        this.submit.click();
    }
}
