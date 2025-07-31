package org.grogu.utility;

import org.openqa.selenium.WebDriver;

public class DriverMangerHolder {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("Driver has not been initialized for this thread.");
        }
        return driver;
    }

    // Quit and remove driver for current thread
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
