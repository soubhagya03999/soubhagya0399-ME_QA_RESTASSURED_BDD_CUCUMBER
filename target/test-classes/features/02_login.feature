Feature: User Login

  Scenario: Successful login with registered credentials
    Given the user is on the login page
    When the user logs in with the registered credentials
    Then the user should be redirected to the dashboard

  Scenario: Failed login with incorrect credentials
    Given the user is on the login page
    When the user logs in with incorrect credentials
    Then the user should see an error message