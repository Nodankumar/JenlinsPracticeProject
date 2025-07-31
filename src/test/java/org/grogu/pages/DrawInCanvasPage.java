package org.grogu.pages;

import org.grogu.enums.BrowserName;
import org.openqa.selenium.By;

public class DrawInCanvasPage extends BasePage{

    By canvasLocator = By.cssSelector("canvas#my-canvas");

    public DrawInCanvasPage(BrowserName browserName) {
        super(browserName);
    }

    public DrawInCanvasPage(BrowserName browserName, int timeoutSec) {
        super(browserName);
        setTimeoutSec(timeoutSec);
    }

    public void drawInCanvas(){
        actions().moveToElement(find(canvasLocator)).clickAndHold().
                moveByOffset(50, 0).moveByOffset(0, -50)
                .release().build().perform();
    }

}
