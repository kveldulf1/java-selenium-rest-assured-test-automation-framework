@Api
Feature: Users endpoints tests

  @CreateUserApi
  Scenario: Create new user successfully
    Given I have new user credentials for API request
    When I send POST create user request
    Then Response code should be 201

  @GetUserApi
  Scenario: I get list of users
    When I send GET request to users endpoint
    Then Response code should be 200
