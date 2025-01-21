Feature: Login functionality

  @Test1
  Scenario: Successful login with random valid user
    When I am on the main page
    And I hover mouse over user icon
    And I click on login button on user menu component
    And I provide valid credentials for random existing user
    And I click on login button on login page
    Then I should be on welcome page  
