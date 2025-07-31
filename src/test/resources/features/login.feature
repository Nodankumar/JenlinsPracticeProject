Feature: Login in practice site

  Scenario Outline: Login Functionality
    Given I use chrome
    When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
    And I log in with the <username> and <password>
    And I click Submit
    Then I should see the message <LoginStatus>
    Examples:
    |username |password |LoginStatus          |
    |"invalid"|"invalid"|"Invalid credentials"|
    |"user"   |"user"   |"Login successful"   |

  @TestExcelAndHeadless
  Scenario: Login with Invalid Credentials
    Given I use chrome in headless mode
    When I navigate to "https://login.salesforce.com/" site
    And I entered the data from the file "LoginData.xlsx"
    And I click login
    Then I am on samePage
