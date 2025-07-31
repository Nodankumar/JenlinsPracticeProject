package org.grogu.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.grogu.enums.BrowserName;
import org.grogu.utility.DriverMangerHolder;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    WebDriverWait webDriverWait;
    int timeoutSec = 5;


    public BasePage(BrowserName browserName){
        if(browserName.toString().equalsIgnoreCase("edge")){
            System.out.println("Edge browser driver setup done successfully");
            System.setProperty("webdriver.edge.driver", System.getProperty("usr.dir")+"//src//test//resources//drivers//msedgedriver.exe");
            driver.set(WebDriverManager.edgedriver().create());
        }else {
            driver.set(WebDriverManager.getInstance(browserName.toString()).create());
        }
        DriverMangerHolder.setDriver(driver.get());
        webDriverWait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutSec));
        driver.get().manage().window().maximize();

    }

    public BasePage(MutableCapabilities mutableCapabilities, String remoteAddress){
        if(WebDriverManager.isOnline(remoteAddress) || remoteAddress.isBlank()) {
            driver.set(RemoteWebDriver.builder().oneOf(mutableCapabilities).address(remoteAddress).build());
        }else {
            driver.set(RemoteWebDriver.builder().oneOf(mutableCapabilities).build());
        }
        DriverMangerHolder.setDriver(driver.get());
        webDriverWait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutSec));
        driver.get().manage().window().maximize();
    }

    public BasePage(BrowserName browserName, WebDriverListener listenerClass){
        WebDriver originalDriver = WebDriverManager.getInstance(browserName.toString()).create();
        driver.set(new EventFiringDecorator<>(listenerClass).decorate(originalDriver));
        DriverMangerHolder.setDriver(driver.get());
        webDriverWait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutSec));
        driver.get().manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setTimeoutSec(int timeoutInSec){
        this.timeoutSec = timeoutInSec;
    }

    public void quit(){
        if(driver!=null) driver.get().quit();
    }

    public void visit(String url){
        driver.get().get(url);
    }

    public WebElement find(By element){
        return driver.get().findElement(element);
    }

    public JavascriptExecutor getJavascriptExecutor(){
        return ((JavascriptExecutor)driver.get());
    }

    public void click(By element){
        find(element).click();
    }

    public void selectByVisibleText(By locator, String text){
        Select select = new Select(find(locator));
        select.selectByVisibleText(text);
    }

    public void selectByVisibleText(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void click(WebElement webElement){
        webElement.click();
    }

    public void typeText(By element, String text){
        find(element).sendKeys(text);
    }

    public void typeText(WebElement webElement, String text){
        webElement.sendKeys(text);
    }

    public void setColor(WebElement element, int redValue, int greenValue, int blueValue, int opacity){
        Color color = new Color(redValue, greenValue, blueValue, opacity);
        String script = String.format("arguments[0].setAttribute('value', '%s');", color.asHex());
        getJavascriptExecutor().executeScript(script, element);
    }

    public void changeRange(int rangeValue, WebElement element){
        for (int i=0;i<=rangeValue; i++){
            element.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public boolean isDisplayed(By locator){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (TimeoutException ex){
            return false;
        }
        return true;
    }

    public boolean isDisplayed(WebElement webElement){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElement));
        }catch (TimeoutException ex){
            return false;
        }
        return true;
    }

    public String getText(WebElement webElement){
        return webElement.getText();
    }

    public void setWindowSize(Dimension dimension){
        driver.get().manage().window().setSize(dimension);
    }

    public WebDriver.Navigation navigation(){
        return driver.get().navigate();
    }

    public void navigateForward(){
        navigation().forward();
    }

    public void navigateBack(){
        navigation().back();
    }

    public void refreshPage(){
        navigation().refresh();
    }

    public WebDriver switchFocusToWindow(String windowHandle){
        return driver.get().switchTo().window(windowHandle);
    }

    public WebDriver openNewWindow(WindowType windowType){
        return driver.get().switchTo().newWindow(windowType);
    }

    //mouse action methods

    public Actions actions(){
        Actions actions = new Actions(driver.get());
        return actions;
    }

    public void mouseOver(By locator){
        WebElement webElement = find(locator);
        actions().moveToElement(webElement).build().perform();
    }

    public void dragAndDrop(By srcLocator, By targetLocator){
        WebElement srcElement = find(srcLocator);
        WebElement targetElement = find(targetLocator);
        actions().dragAndDrop(srcElement, targetElement).build().perform();
    }

}
