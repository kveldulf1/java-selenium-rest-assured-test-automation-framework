Feature: Login functionality

Background:
  Given I am on the main page 

  @RegisterUser
  Scenario Outline: New user is able to register from main page
    When I hover mouse over user icon
    And I click on register button on user menu component
    And I provide user first name "<firstName>"
    And I provide user last name "<lastName>"
    And I provide user email "<email>"
    And I provide user password "<password>"
    And I click on register button on register page
    Then Alert text should be contains "User created"

    Examples:
      | firstName | lastName | email               | password  |
      | John      | Doe      | john.doe@test.com   | password1 |
      | Jane      | Smith    | jane.smith@test.com | password2 |

  @UserLogsIn
  Scenario: Successful login from main page with random valid user
    When I hover mouse over user icon
    And I click on login button on user menu component
    And I provide valid credentials for random existing user
    And I click on login button on login page
    Then I should be on welcome page

  @UserLogsInWithInvalidUser
  Scenario: Unsuccessful login from main page with invalid user credentials
    When I hover mouse over user icon
    And I click on login button on user menu component
    And I provide invalid credentials
    And I click on login button on login page
    Then Alert text should contains "Invalid username or password"

