Feature: Testing in practice site

  Scenario: mouse over on compass image
    Given I use chrome for this practice site
    When I visit "https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html"
    And I move my mouse on to the compass image
    Then I see "compass" text

  Scenario: Test dragAndDrop
    Given I use edge for drag and drop site
    When I visit "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html" drag and drop site
    And I drag my draggable panel to the target location
    Then I verify draggable panel and target location both are equal

  @ClickAndHold
  Scenario: drawing in canvas
    Given I use chrome for drawing in canvas
    When I visit "https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html" draw in canvas page
    And I draw in canvas