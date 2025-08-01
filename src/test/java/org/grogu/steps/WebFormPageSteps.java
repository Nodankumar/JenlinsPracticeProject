package org.grogu.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.grogu.enums.BrowserName;
import org.grogu.pages.WebFormPage;

import java.util.List;
import java.util.Map;

public class WebFormPageSteps {

    WebFormPage webFormPage;

    @ParameterType("chrome|edge|firefox")
    public BrowserName browserName(String browserType){
        return BrowserName.valueOf(browserType);
    }

    @Given("I use {browserName} browser")
    public void i_use_edge_browser(BrowserName browserName) {
        webFormPage = new WebFormPage(browserName, 10);
    }

    @When("I navigate to the web form {string}")
    public void i_navigate_to_the_web_form(String url) {
        webFormPage.visit(url);
    }

    @When("I enter the following data")
    public void i_enter_the_following_data(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        webFormPage.enterText(data.get(0).get("TextInput"), data.get(0).get("Password"), data.get(0).get("TextArea"));
    }

    @When("I select {string} from dropdown select")
    public void iSelectFromDropdownSelect(String option) {
        webFormPage.selectOption(option);
    }

    @When("I select {string} from dropdown datalist")
    public void i_select_from_dropdown_datalist(String option) {
        webFormPage.selectDatalist(option);
    }

    @When("I upload file {string}")
    public void i_upload_file(String filPath) {
        webFormPage.uploadFile(filPath);
    }

    @When("I select the default checkbox and default radio")
    public void i_select_the_default_checkbox_and_default_radio() {
        webFormPage.selectDefaultCheckBoxAndRadio();
    }

    @When("I choose green color")
    public void i_choose_green_color() {
        webFormPage.chooseColor(0, 1, 0, 1);
    }

    @When("I enter {string} in the date picker")
    public void i_enter_in_the_date_picker(String date) {
        webFormPage.selectDate(date);
    }

    @And("I changed range by {int}")
    public void iChangedRangeTo(int range) {
        webFormPage.chooseRange(range);
    }

    @Then("I submit the form")
    public void i_submit_the_form() {
        webFormPage.submitForm();
    }

}
