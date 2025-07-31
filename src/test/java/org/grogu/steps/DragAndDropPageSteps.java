package org.grogu.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.grogu.enums.BrowserName;
import org.grogu.pages.DragAndDropPage;

public class DragAndDropPageSteps {

    DragAndDropPage dragAndDropPage;

    @Given("I use {browserName} for drag and drop site")
    public void i_use_edge_for_drag_and_drop_site(BrowserName browserName) {
        dragAndDropPage = new DragAndDropPage(browserName);
    }

    @When("I visit {string} drag and drop site")
    public void i_visit_drag_and_drop_site(String url) {
        dragAndDropPage.visit(url);
    }

    @When("I drag my draggable panel to the target location")
    public void i_drag_my_draggable_panel_to_the_target_location() {
        dragAndDropPage.dragAndDropPanel();
    }

    @Then("I verify draggable panel and target location both are equal")
    public void i_verify_draggable_panel_and_target_location_both_are_equal() {
        dragAndDropPage.issLocationEqual();
    }
}
