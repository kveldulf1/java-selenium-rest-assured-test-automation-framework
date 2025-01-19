package stepdefs;

import io.cucumber.java.en.*;
import pageobjects.*;
import org.junit.jupiter.api.Assertions;

public class LoginSteps extends BaseSteps {
    private MainPage mainPage;
    private LoginPage loginPage;
    private WelcomePage welcomePage;
    
    @Given("I am on the main page")
    public void i_am_on_main_page() {
        mainPage = new MainPage(driver).go();
    }
    
    @When("I login as random existing user")
    public void i_login_as_random_user() {
        welcomePage = mainPage
            .headerComponent
            .hoverMouseOverUserIcon()
            .clickLoginButton()
            .loginAsRandomExistingUser();
    }
    
    @Then("I should be on welcome page")
    public void i_should_be_on_welcome_page() {
        Assertions.assertTrue(welcomePage.isUrlCorrect(),
            "URL is not correct after login");
    }
} 