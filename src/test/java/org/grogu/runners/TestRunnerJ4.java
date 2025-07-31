package org.grogu.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/login.feature", glue = "org.grogu.steps",
        plugin = {"pretty","html:target/reports/report.html", "junit:target/reports/report.xml",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunnerJ4 {

}
