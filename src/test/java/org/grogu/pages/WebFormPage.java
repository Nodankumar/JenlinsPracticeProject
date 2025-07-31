package org.grogu.pages;

import org.grogu.enums.BrowserName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebFormPage extends BasePage{

    @FindBy(css = "input[id='my-text-id']")
    WebElement textInput;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordEle;

    @FindBy(xpath = "//textarea[starts-with(@name,'my-textarea')]")
    WebElement textAreaEle;

    @FindBy(css = "select.form-select")
    WebElement dropdownSelect;

    @FindBy(xpath = "//input[@name='my-datalist']")
    WebElement dropdownDatalist;

    @FindBy(xpath = "//label//child::input[@type='file']")
    WebElement uploadAFile;

    @FindBy(id = "my-check-2")
    WebElement checkbox;

    @FindBy(id = "my-radio-2")
    WebElement radioBox;

    @FindBy(xpath = "//input[@type='color']")
    WebElement colorBox;

    @FindBy(css = "input[name='my-date']")
    WebElement datePicker;

    @FindBy(css = "input[type='range']")
    WebElement rangeEle;

    @FindBy(css = "button.btn")
    WebElement submit;

    public WebFormPage(BrowserName browserName) {
        super(browserName);
        PageFactory.initElements(getDriver(), this);
    }

    public WebFormPage(BrowserName browserName, int timeoutSec){
        this(browserName);
        setTimeoutSec(timeoutSec);
        PageFactory.initElements(getDriver(), this);
    }

    public void enterText(String inputText, String password, String textArea){
        typeText(textInput, inputText);
        typeText(passwordEle, password);
        typeText(textAreaEle, textArea);
    }

    public void selectOption(String text){
        selectByVisibleText(dropdownSelect, text);
    }

    public void selectDatalist(String dataListOpt){
        dropdownDatalist.click();
        typeText(dropdownDatalist, dataListOpt);
    }

    public void uploadFile(String filePath){
        typeText(uploadAFile, filePath);
    }

    public void selectDefaultCheckBoxAndRadio(){
        click(checkbox);
        click(radioBox);
    }

    public void chooseColor(int redValue, int greenValue, int blueValue, int opacity){
        setColor(colorBox, redValue,greenValue,blueValue, opacity);
    }

    public void selectDate(String date){
        typeText(datePicker, date);
    }

    public void chooseRange(int rangeValue){
        changeRange(rangeValue, rangeEle);
    }

    public void submitForm(){
       submit.submit();
    }

}
