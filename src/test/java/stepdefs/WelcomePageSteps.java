package stepdefs;

import io.cucumber.java.en.*;
import pageobjects.WelcomePage;

import static org.junit.jupiter.api.Assertions.*;

public class WelcomePageSteps extends BaseSteps {

    private WelcomePage welcomePage;

    public WelcomePageSteps() {
        this.welcomePage = new WelcomePage(driver);
    }

    @Then("I should be on welcome page")
    public void i_should_be_on_welcome_page() {
        assertTrue(welcomePage.isUrlCorrect());
    }
}
