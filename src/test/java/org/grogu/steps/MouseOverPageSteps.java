package org.grogu.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.grogu.enums.BrowserName;
import org.grogu.pages.MouseOverPage;

import static org.testng.Assert.assertTrue;

public class MouseOverPageSteps {

    MouseOverPage mouseOverPage;

    @Given("I use {browserName} for this practice site")
    public void i_use_chrome_for_this_practice_site(BrowserName browserName) {
        mouseOverPage = new MouseOverPage(browserName);
    }

    @When("I visit {string}")
    public void i_visit(String url) {
        mouseOverPage.visit(url);
    }

    @When("I move my mouse on to the compass image")
    public void i_move_my_mouse_on_to_the_compass_image() {
        mouseOverPage.moveToCompass();
    }

    @Then("I see {string} text")
    public void i_see_text(String imageName) {
        assertTrue(mouseOverPage.isCaptionPresent(imageName));
    }
}
