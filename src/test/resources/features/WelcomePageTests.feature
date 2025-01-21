Feature: Welcome page tests

  @LoggedInUserIsOnTheRightPage
  Scenario: Logged in user is on the right page
    Given I am on the welcome page as logged in user
    Then I should be on welcome page
