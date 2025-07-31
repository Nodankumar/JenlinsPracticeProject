Feature: test shadow dom
  Scenario:Get Text from shadow dom
    Given I use chrome for shadow dom
    When I visit "https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html" shadow dom page
    And I get text from shadow root
    Then I verify text is "Hello Shadow DOM"