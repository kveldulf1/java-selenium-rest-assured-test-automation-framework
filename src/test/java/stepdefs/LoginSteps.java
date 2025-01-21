package stepdefs;

import io.cucumber.java.en.*;
import pageobjects.*;
import org.junit.jupiter.api.Assertions;

public class LoginSteps extends BaseSteps {

    private LoginPage loginPage; 

    public LoginSteps() {
        this.loginPage = new LoginPage(driver);
    }

    @When("I login as random existing user")
    public void i_login_as_random_user() {
        loginPage.loginAsRandomExistingUser();
    }

    @Then("I should be on welcome page")
    public void i_should_be_on_welcome_page() {
        Assertions.assertTrue(new WelcomePage(driver).isUrlCorrect(),
            "URL is not correct after login");
    }

    @When("I provide valid credentials for random existing user")
    public void i_provide_valid_credentials_for_random_existing_user() {
        loginPage.provideValidCredentialsForRandomExistingUser();
    }

    @When("I click on login button on login page")
    public void i_click_on_login_button_on_login_page() {
        loginPage.clickLoginButton();
    }
} 