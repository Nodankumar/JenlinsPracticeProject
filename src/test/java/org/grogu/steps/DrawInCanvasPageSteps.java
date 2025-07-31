package org.grogu.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.grogu.enums.BrowserName;
import org.grogu.pages.DrawInCanvasPage;

public class DrawInCanvasPageSteps {

    DrawInCanvasPage drawInCanvasPage;

    @Given("I use {browserName} for drawing in canvas")
    public void i_use_chrome_for_drawing_in_canvas(BrowserName browserName) {
        drawInCanvasPage = new DrawInCanvasPage(browserName);
    }

    @When("I visit {string} draw in canvas page")
    public void i_visit_draw_in_canvas_page(String url) {
        drawInCanvasPage.visit(url);
    }

    @When("I draw in canvas")
    public void i_draw_in_canvas() {
        drawInCanvasPage.drawInCanvas();
    }
}
