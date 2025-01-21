package stepdefs;

import io.cucumber.java.en.*;
import pageobjects.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps extends BaseSteps {

    private LoginPage loginPage; 

    public LoginSteps() {
        this.loginPage = new LoginPage(driver);
    }

    @When("I login as random existing user")
    public void i_login_as_random_user() {
        loginPage.loginAsRandomExistingUser();
    }

    @When("I provide valid credentials for random existing user")
    public void i_provide_valid_credentials_for_random_existing_user() {
        loginPage.provideValidCredentialsForRandomExistingUser();
    }

    @When("I click on login button on login page")
    public void i_click_on_login_button_on_login_page() {
        loginPage.clickLoginButton();
    }

    @When("I provide invalid credentials")
    public void I_provide_invalid_credentials() {
        loginPage.provideInvalidCredentials();
    }

    @Then("Alert text should contains {string}")
    public void Alert_text_should_contains(String alertText) {
        assertTrue(loginPage.getAlertText().contains(alertText));
    }

  
   
} 