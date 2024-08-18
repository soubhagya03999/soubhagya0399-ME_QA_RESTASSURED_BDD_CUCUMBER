package com.example.restassured;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

public class RegisterUserSteps {
    public static String dynamicEmail;
    public static String dynamicPassword;
    private RequestSpecification request;
    private Response response;
    private String baseUri = "https://content-qtripdynamic-qa-backend.azurewebsites.net/api/v1/register";

    @Given("I have a valid user registration payload")
    public void i_have_a_valid_user_registration_payload() {
        dynamicEmail = "user_" + UUID.randomUUID().toString() + "@example.com";
        dynamicPassword = "Pass" + UUID.randomUUID().toString().substring(0, 5);

        request = given()
                .contentType("application/json")
                .body("{\"email\":\"" + dynamicEmail + "\", \"password\":\"" + dynamicPassword
                        + "\", \"confirmpassword\":\"" + dynamicPassword + "\"}");
    }

    @Given("I have a registration payload with an already registered email")
    public void i_have_a_registration_payload_with_an_already_registered_email() {
        String registeredEmail = dynamicEmail;
        String password = dynamicPassword;

        request = given()
                .contentType("application/json")
                .body("{\"email\":\"" + registeredEmail + "\", \"password\":\"" + password
                        + "\", \"confirmpassword\":\"" + password + "\"}");
    }

    @Given("I have a registration payload with a short password")
    public void i_have_a_registration_payload_with_a_short_password() {
        String dynamicEmail = "shortpass_" + UUID.randomUUID().toString() + "@example.com";
        String shortPassword = "123";

        request = given()
                .contentType("application/json")
                .body("{\"email\":\"" + dynamicEmail + "\", \"password\":\"" + shortPassword
                        + "\", \"confirmpassword\":\"" + shortPassword + "\"}");
    }

    @When("I send a POST request to the user registration endpoint")
    public void i_send_a_post_request_to_the_user_registration_endpoint() {
        response = request.post(baseUri);
    }

    @Then("I should receive a {int} status code")
    public void i_should_receive_a_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }
}