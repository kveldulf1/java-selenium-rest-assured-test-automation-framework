@Api
Feature: Authentication API Tests

  @ApiLoginWithValidCredentials
  Scenario: Login with valid credentials
    Given I have valid user credentials
    When I send POST login request
    Then Response code should be 200
    And Response should contain access token