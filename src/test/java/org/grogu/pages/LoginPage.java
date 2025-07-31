package org.grogu.pages;

import org.grogu.enums.BrowserName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    @FindBy(id = "username")
    @CacheLookup
    WebElement usernameInput;

    @FindBy(id = "password")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(css = "button")
    @CacheLookup
    WebElement submitButton;

    @FindBy(className = "alert")
    WebElement alertBox;


    public LoginPage(BrowserName browserName) {
        super(browserName);
        PageFactory.initElements(getDriver(), this);
    }

    public LoginPage(BrowserName browserName, int timeoutSec){
        this(browserName);
        setTimeoutSec(timeoutSec);
        PageFactory.initElements(getDriver(), this);
    }

    public void enterCredentials(String username, String password){
        typeText(usernameInput, username);
        typeText(passwordInput, password);
    }

    public void clickLogin(){
        click(submitButton);
    }

    public boolean successBoxPresent() {
        return isDisplayed(alertBox);
    }

    public String getAlertBoxText(){
        return getText(alertBox);
    }

}
