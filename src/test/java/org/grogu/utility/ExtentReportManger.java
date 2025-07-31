package org.grogu.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManger {

    private static ExtentReports extentReports;

    public static synchronized ExtentReports getExtentReport(){
        if(extentReports == null){
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/reports/extentReport.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
        }
        return extentReports;
    }
}
