package stepdefs;

import io.cucumber.java.en.*;
import pojo.authentication.LoginRequest;
import api.AuthenticationApi;
import com.google.gson.JsonObject;
import utils.CommonApiCalls;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationApiSteps extends BaseApiSteps {
    
    private JsonObject validUser;
    private LoginRequest loginRequest;
    

    @Given("I have valid user credentials")
    public void I_have_valid_user_credentials() {
        loginRequest = new CommonApiCalls().getValidUserCredentials();
    }

    @When("I send POST login request")
    public void iSendPostLoginRequest() {
        response = AuthenticationApi.login(loginRequest);
    }

    @Then("I should receive status code {int}")
    public void iShouldReceiveStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("Response should contain access token")
    public void responseShouldContainAccessToken() {
        String accessToken = response.jsonPath().getString("access_token");
        assertNotNull(accessToken, "Access token should not be null");
        assertFalse(accessToken.isEmpty(), "Access token should not be empty");
    }
} 