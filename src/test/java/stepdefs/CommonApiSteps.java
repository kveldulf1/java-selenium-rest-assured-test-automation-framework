package stepdefs;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import utils.CommonApiCalls;
import utils.TestContext;

public class CommonApiSteps extends BaseApiSteps {

    private final CommonApiCalls commonApiCalls;
    private final TestContext testContext;

    public CommonApiSteps(TestContext testContext) {
        this.commonApiCalls = new CommonApiCalls();
        this.testContext = testContext;
    }

    @Then("Response code should be {int}")
    public void responseCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = testContext.getLastResponse().getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, 
            "Expected status code " + expectedStatusCode + " but got " + actualStatusCode);
    }
}