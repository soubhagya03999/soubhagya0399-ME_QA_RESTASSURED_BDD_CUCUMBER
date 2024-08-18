package com.example.restassured;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {

    private Response response;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Setup or URL configuration if needed
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net/api/v1";
    }

    @When("the user logs in with the registered credentials")
    public void the_user_logs_in_with_registered_credentials() {
        // Use the static variables from RegisterUserSteps
        String email = RegisterUserSteps.dynamicEmail;
        String password = RegisterUserSteps.dynamicPassword;

        response = RestAssured.given()
                .contentType("application/json")
                .body(String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password))
                .post("/login"); // The endpoint from your API
    }

    @When("the user logs in with incorrect credentials")
    public void the_user_logs_in_with_incorrect_credentials() {
        // Use invalid credentials for testing
        String email = "invalid@example.com";
        String password = "wrongpassword";

        response = RestAssured.given()
                .contentType("application/json")
                .body(String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password))
                .post("/login"); // The endpoint from your API
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        response.then().statusCode(201)
                .body("success", equalTo(true)); // Adjust based on actual response
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        response.then().statusCode(404)
                .body("message", equalTo("email does not exist")); // Adjust based on actual response
    }
}