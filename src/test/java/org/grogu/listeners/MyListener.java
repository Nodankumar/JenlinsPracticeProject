package org.grogu.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class MyListener implements WebDriverListener {


    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("current url: "+url);
        WebDriverListener.super.afterGet(driver, url);
    }

}
