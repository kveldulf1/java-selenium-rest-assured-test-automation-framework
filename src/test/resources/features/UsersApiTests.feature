@Api
Feature: Users API

@CreateUserApi
Scenario: Create new user successfully

    Given I have new user credentials for API request
    When I create a new user
    Then Response code should be 201
