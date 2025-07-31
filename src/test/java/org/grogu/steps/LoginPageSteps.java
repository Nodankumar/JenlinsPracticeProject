package org.grogu.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.grogu.enums.BrowserName;
import org.grogu.pages.LoginPage;
import org.grogu.utility.ExcelManager;
import org.openqa.selenium.StaleElementReferenceException;

import static org.testng.Assert.assertTrue;

public class LoginPageSteps {

    LoginPage loginPage;

    @Given("I use {browserName}")
    public void i_use(BrowserName browserName) {
        loginPage = new LoginPage(browserName);
    }

    @When("I navigate to {string}")
    public void i_navigate_to(String url) {
        loginPage.visit(url);
    }

    @When("I log in with the {string} and {string}")
    public void i_log_in_with_the_and(String username, String password) {
        loginPage.enterCredentials(username, password);

    }

    @When("I click Submit")
    public void i_click_submit() {
        loginPage.clickLogin();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String loginStatus) {
        try {
            assertTrue(loginPage.successBoxPresent());
            String actualMessage = loginPage.getAlertBoxText();
            assertTrue(actualMessage.contains(loginStatus), "Expected: " + loginStatus + ", but got: " + actualMessage);
        }catch (StaleElementReferenceException ex){
            String actualMessage = loginPage.getAlertBoxText();
            assertTrue(actualMessage.contains(loginStatus), "Expected: " + loginStatus + ", but got: " + actualMessage);
        }
    }
}
