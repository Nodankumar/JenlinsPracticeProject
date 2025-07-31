package org.grogu.pages;

import org.grogu.enums.BrowserName;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class ShadowDomPage extends BasePage{

    By shadowContentLoc = By.id("content");

    By shadowTextLoc =  By.cssSelector("p");

    public ShadowDomPage(BrowserName browserName) {
        super(browserName);
    }

    public ShadowDomPage(BrowserName browserName, int timeoutSec) {
        super(browserName);
        setTimeoutSec(timeoutSec);
    }

    public ShadowDomPage(BrowserName browserName, WebDriverListener listener) {
        super(browserName, listener);
        setTimeoutSec(timeoutSec);
    }


    public String getShadowText() {
        SearchContext  shadowRoot = find(shadowContentLoc).getShadowRoot();
        WebElement textElement = shadowRoot.findElement(shadowTextLoc);
        return textElement.getText();
    }
}
