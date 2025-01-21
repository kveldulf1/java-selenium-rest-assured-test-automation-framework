Feature: Login functionality

  @Test1
  Scenario: Successful login with random valid user
    Given I am on the main page
    When I login as random existing user
    Then I should be on welcome page
