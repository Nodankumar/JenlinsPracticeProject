package org.grogu.pages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.grogu.enums.BrowserName;
import org.grogu.utility.ExcelManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SalesForceLoginPage extends BasePage {

    @FindBy(id = "username")
    WebElement usernameEle;

    @FindBy(css = "input#password")
    WebElement passwordEle;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButton;

    public SalesForceLoginPage(BrowserName browserName) {
        super(browserName);
        PageFactory.initElements( getDriver(),this);
    }

    public SalesForceLoginPage(MutableCapabilities mutableCapabilities, String  remoteAddress) {
        super(mutableCapabilities,remoteAddress);
        PageFactory.initElements( getDriver(),this);
    }

    public SalesForceLoginPage(BrowserName browserName, WebDriverListener listenerClass) {
        super(browserName, listenerClass);
        PageFactory.initElements( getDriver(),this);
    }
    public SalesForceLoginPage(BrowserName browserName,int timeoutSec) {
        super(browserName);
        setTimeoutSec(timeoutSec);
        PageFactory.initElements( getDriver(),this);
    }

    public void enterCredentialsFromFile(String filepath) throws IOException, InvalidFormatException {
        List<Map<String, String>> loginData = ExcelManager.getXlsxData(filepath);
        typeText(usernameEle, loginData.get(0).get("username"));
        typeText(passwordEle, loginData.get(0).get("password"));
    }

    public void clickLogin(){
        click(submitButton);
    }

    public String isOnSamePage(){
        return driver.get().getTitle();
    }
}
