package org.grogu.pages;

import org.grogu.enums.BrowserName;
import org.openqa.selenium.By;

public class DragAndDropPage extends BasePage{

    By srcLocator = By.cssSelector("div#draggable");

    By targetLocator = By.cssSelector("div#target");

    public DragAndDropPage(BrowserName browserName) {
        super(browserName);
    }

    public DragAndDropPage(BrowserName browserName, int timeoutSec){
        this(browserName);
    }

    public void dragAndDropPanel(){
        dragAndDrop(srcLocator, targetLocator);
    }

    public boolean issLocationEqual(){
        return find(srcLocator).getLocation().equals(find(targetLocator).getLocation());
    }
}
