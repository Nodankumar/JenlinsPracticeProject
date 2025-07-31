package org.grogu.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.grogu.enums.BrowserName;
import org.grogu.listeners.MyListener;
import org.grogu.pages.ShadowDomPage;

import static org.testng.Assert.assertNotEquals;

public class ShadowDomPageSteps {

    ShadowDomPage shadowDomPage;
    MyListener myListener;
    String shadowText;

    public ShadowDomPageSteps(MyListener listener) {
        this.myListener = listener;
    }

    @Given("I use {browserName} for shadow dom")
    public void i_use_chrome_for_shadow_dom(BrowserName browserName) {
        shadowDomPage = new ShadowDomPage(browserName, myListener);
    }

    @When("I visit {string} shadow dom page")
    public void i_visit_shadow_dom_page(String url) {
        shadowDomPage.visit(url);
    }

    @When("I get text from shadow root")
    public void i_get_text_from_shadow_root() {
        shadowText = shadowDomPage.getShadowText();
    }

    @Then("I verify text is {string}")
    public void i_verify_text_is(String expectedText) {
        assertNotEquals(shadowText, expectedText, "Failing Knowingly to test screenshot capture feature");
    }
}
