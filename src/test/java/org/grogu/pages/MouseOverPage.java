package org.grogu.pages;

import org.grogu.enums.BrowserName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class MouseOverPage extends BasePage{

    By compassLoc = By.cssSelector("img[src$='compass.png']");


    public MouseOverPage(BrowserName browserName) {
        super(browserName);
    }

    public MouseOverPage(BrowserName browserName, int timeoutSec){
        this(browserName);
        setTimeoutSec(timeoutSec);
    }

    public void moveToCompass(){
        mouseOver(compassLoc);
    }

    public boolean isCaptionPresent(String imageName){
        WebElement imageElement = find(compassLoc);
        WebElement caption = find(RelativeLocator.with(By.tagName("div")).near(imageElement));
        return caption.getText().toLowerCase().contains(imageName);
    }
}
