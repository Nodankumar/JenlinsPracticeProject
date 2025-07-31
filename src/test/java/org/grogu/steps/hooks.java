package org.grogu.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.grogu.utility.DriverMangerHolder;
import org.grogu.utility.ExtentReportManger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class hooks {

    private static final ExtentReports extentReports = ExtentReportManger.getExtentReport();
    private static final ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();
    ExtentTest extentTest;

    @Before
    public void beforeScenario(Scenario scenario) {
        extentTest = extentReports.createTest(scenario.getName());
        threadLocalExtentTest.set(extentTest);
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        WebDriver driver = DriverMangerHolder.getDriver();

        if (scenario.isFailed() && driver != null) {
            // Attach to Cucumber report
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", scenario.getName());

            // Attach to Extent report
            String base64Img = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            threadLocalExtentTest.get().fail("Scenario failed").addScreenCaptureFromBase64String(base64Img, scenario.getName());
        } else {
            threadLocalExtentTest.get().pass("Scenario passed");
        }
        extentReports.flush();
        DriverMangerHolder.quitDriver();
    }

}
