Feature: Web pages test

  Scenario: Test search on home page
    Given I open home page
    When I search for cats
    Then I verify that page name is search results

  Scenario: Test click on recent releases button
    Given I open home page
    When I click on recent releases
    Then I verify that page headline is recent releases

  @fail
  Scenario: Test enter login and password
    Given I open login page
    When I enter login abc@gmail.com and password 123456
    Then I verify that loginError message contains: email address and password combination isn't recognized

  @fail
  Scenario: Test click forgot password button
    Given I open login page
    When I click forgot password button
    And I enter email abc@gmail.com
    Then I verify that emailError message contains: email address isn't recognized

  Scenario: Test view design's color change sheet
    Given I open design page
    When I click view color change sheet button
    Then I verify that page's headline is thread list

  Scenario: Test add design to wish list
    Given I open design page
    When I click add to wish list button
    Then I verify that page's name is login