Feature: Fill details in web form in practice site

  Scenario: Enter all the required details in web form
    Given I use firefox browser
    When I navigate to the web form "https://bonigarcia.dev/selenium-webdriver-java/web-form.html"
    And I enter the following data
      | TextInput            | Password          | TextArea                    |
      | "some sample text"   | "strong password" | "Lorem ipsum in text area"  |
    And I select "One" from dropdown select
    And I select "New York" from dropdown datalist
    And I upload file "C:\Users\nodan\Downloads\testscript.bat"
    And I select the default checkbox and default radio
    And I choose green color
    And I enter "03/06/2024" in the date picker
    And I changed range by 5
    Then I submit the form
