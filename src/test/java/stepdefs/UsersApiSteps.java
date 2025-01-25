package stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.users.UsersApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.users.CreateUserRequest;
import utils.CommonApiCalls;
import io.restassured.response.Response;

public class UsersApiSteps extends BaseApiSteps {

    private CreateUserRequest createUserRequest;
    private final UsersApi usersApi;
    private final TestContext testContext;

    public UsersApiSteps(TestContext testContext) {
        this.testContext = testContext;
        this.usersApi = new UsersApi();
    }

    @Given("I have new user credentials for API request")
    public void I_have_new_user_credentials_for_API_request() {
        createUserRequest = new CommonApiCalls().prepareUserRequest();
    }

    @When("I send POST create user request")
    public void I_send_POST_create_user_request() {
        response = usersApi.createUser(createUserRequest);
    }

    @When("I create a new user")
    public void iCreateANewUser() {
        CreateUserRequest request = new CommonApiCalls().prepareUserRequest();
        testContext.setLastResponse(usersApi.createUser(request));
    }
}
