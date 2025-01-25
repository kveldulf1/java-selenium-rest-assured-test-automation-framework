@Api
Feature: Users endpoint API Tests

@CreateUserApi
Scenario: Create and verify user

    Given I have new user credentials for API request
    When I send POST create user request
    Then Response code should be 201
