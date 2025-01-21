package stepdefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import pojo.authentication.LoginRequest;
import constants.ApiEndpoints;
import helpers.UserTestData;
import api.AuthenticationApi;

import static io.restassured.RestAssured.given;

public class AuthenticationApiSteps {
    
    private LoginRequest loginRequest;
    private Response response;

    @Given("I have valid user credentials")
    public void I_have_valid_user_credentials() {
        var validUser = UserTestData.getRandomValidUser();
        loginRequest = new LoginRequest(
            UserTestData.getEmail(validUser),
            UserTestData.getPassword(validUser)
        );
    }

    @When("I send POST login request")
    public void iSendPostLoginRequest() {
        response = AuthenticationApi.login(loginRequest);
    }

    @Then("I should receive status code {int}")
    public void iShouldReceiveStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @And("Response should contain access token")
    public void responseShouldContainAccessToken() {
        String accessToken = response.path("access_token");
        Assertions.assertNotNull(accessToken, "Access token is null");
    }
} 