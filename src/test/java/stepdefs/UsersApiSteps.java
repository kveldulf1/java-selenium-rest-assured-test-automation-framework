package stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.users.UsersApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.users.CreateUserRequest;
import utils.CommonApiCalls;

public class UsersApiSteps extends BaseApiSteps {

    private CreateUserRequest createUserRequest;
    private final UsersApi usersApi;

    public UsersApiSteps() {
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

    @Then("Response code should be {int}")
    public void Response_code_should_be(int responseCode) {
        assertEquals(responseCode, response.getStatusCode());
    }
}
