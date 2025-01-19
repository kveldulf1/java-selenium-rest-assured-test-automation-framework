Feature: Login Tests

Scenario: User logs in 

Given User is on the main page
When User hovers cursor over user icon
And User clicks on the register button
And User provides details and clicks on register button
Then User created alert text should be displayed
