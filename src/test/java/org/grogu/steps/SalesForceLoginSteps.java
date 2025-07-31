package org.grogu.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.grogu.enums.BrowserName;
import org.grogu.pages.SalesForceLoginPage;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class SalesForceLoginSteps {

    SalesForceLoginPage salesForceLoginPage;

    String pastTitle;

    @Given("I use chrome in headless mode")
    public void iUseChromeForSalesforce() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        salesForceLoginPage = new SalesForceLoginPage(chromeOptions, "http://localhost:4444");
    }

    @When("I navigate to {string} site")
    public void iNavigateToSite(String url) {
        salesForceLoginPage.visit(url);
        pastTitle = salesForceLoginPage.getDriver().getTitle();
    }

    @And("I entered the data from the file {string}")
    public void iEnteredTheDataFromTheFile(String filePath) {
        try {
            salesForceLoginPage.enterCredentialsFromFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @And("I click login")
    public void iClickLogin() {
        salesForceLoginPage.clickLogin();
    }

    @Then("I am on samePage")
    public void iAmOnSamePage() {
        assertEquals(pastTitle, salesForceLoginPage.isOnSamePage());
    }

}
