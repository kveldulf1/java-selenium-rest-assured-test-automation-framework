@Api
Feature: Users endpoints tests

  @CreateUserApi
  Scenario: Create new user and confirm it was created
    Given I have new user credentials for API request
    When I send POST create user request
    And I retrive id value from response body
    Then Response code should be 201
    When I send GET request to users endpoint
    Then Response code should be 200
    And Reponse body should contain id of created user

  @GetUserApi
  Scenario: I get list of users
    When I send GET request to users endpoint
    Then Response code should be 200
