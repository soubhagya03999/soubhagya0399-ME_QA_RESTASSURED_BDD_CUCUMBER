Feature: User Registration API

  Scenario: Successful user registration
    Given I have a valid user registration payload
    When I send a POST request to the user registration endpoint
    Then I should receive a 201 status code

  Scenario: Failed registration due to email already existing
    Given I have a registration payload with an already registered email
    When I send a POST request to the user registration endpoint
    Then I should receive a 400 status code

  Scenario: Failed registration due to password being less than 6 characters
    Given I have a registration payload with a short password
    When I send a POST request to the user registration endpoint
    Then I should receive a 400 status code