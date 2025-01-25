package stepdefs;

import io.cucumber.java.en.*;
import pojo.authentication.LoginRequest;
import api.authentication.AuthenticationApi;
import utils.CommonApiCalls;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationApiSteps extends BaseApiSteps {
    
    private LoginRequest loginRequest;
    private final AuthenticationApi authenticationApi;
    private final TestContext testContext;
    
    public AuthenticationApiSteps(TestContext testContext) {
        this.testContext = testContext;
        this.authenticationApi = new AuthenticationApi();
    }

    @Given("I have valid user credentials")
    public void I_have_valid_user_credentials() {
        loginRequest = new CommonApiCalls().getValidUserCredentials();
    }

    @When("I send POST login request")
    public void iSendPostLoginRequest() {
        response = authenticationApi.login(loginRequest);
        testContext.setLastResponse(response);
    }

    @Then("Response should contain access token")
    public void responseShouldContainAccessToken() {
        String accessToken = response.jsonPath().getString("access_token");
        assertNotNull(accessToken, "Access token should not be null");
        assertFalse(accessToken.isEmpty(), "Access token should not be empty");
    }
} 