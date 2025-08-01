package org.grogu.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "org.grogu.steps",
        plugin = {"pretty","html:target/reports/report.html", "junit:target/reports/report.xml"}
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {
}
