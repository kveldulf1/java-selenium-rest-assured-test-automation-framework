package stepdefs;

import io.cucumber.java.en.*;
import utils.CommonApiCalls;

public class CommonSteps extends BaseSteps {

    @Given("I am on the welcome page as logged in user")
    public void I_am_on_the_welcome_page_as_logged_in_user() {
        new CommonApiCalls().goToWelcomePageAsLoggedInUser(driver);
    }
}
