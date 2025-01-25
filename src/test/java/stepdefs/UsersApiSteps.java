package stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.users.UsersApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.users.CreateUserRequest;
import utils.CommonApiCalls;
import pojo.users.CreateUserResponse;
import utils.TestContext;
import java.util.List;

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
        testContext.setLastResponse(response);
    }

    @When("I send GET request to users endpoint")
    public void I_send_GET_request_to_users_endpoint() {
        response = usersApi.getUsers();
        testContext.setLastResponse(response);
    }


    @When("I retrive id value from response body")
    public void I_retrive_id_value_from_response_body() {
        CreateUserResponse userResponse = testContext.getLastResponse().as(CreateUserResponse.class);
        Long userId = userResponse.getId();
        testContext.setUserId(userId);
    }

    @Then("Response body should contain id of created user")
    public void response_body_should_contain_id_of_created_user() {
        Long expectedId = testContext.getUserId();
        List<Integer> userIds = testContext.getLastResponse().jsonPath().getList("id");
        
        assertTrue(userIds.contains(expectedId.intValue()), 
            "Created user with ID " + expectedId + " not found in response");
    }
    }

