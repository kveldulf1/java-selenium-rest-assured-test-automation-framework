package stepdefs;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.*;
import pageobjects.RegisterPage;

public class RegisterPageSteps extends BaseSteps {

    private RegisterPage registerPage;

    public RegisterPageSteps() {
        this.registerPage = new RegisterPage(driver);
    }

    @When("I provide user first name {string}")
    public void i_provide_user_first_name(String firstName) {
        registerPage.typeUsername(firstName);
    }

    @When("I provide user last name {string}")
    public void i_provide_user_last_name(String lastName) {
        registerPage.typeLastname(lastName);
    }

    @When("I provide user email {string}")
    public void i_provide_user_email(String email) {
        registerPage.typeEmail(email);
    }

    @When("I provide user password {string}")
    public void i_provide_user_password(String password) {
        registerPage.typePassword(password);
    }

    @When("I click on register button on register page")
    public void i_click_on_register_button_on_register_page() {
        registerPage.clickRegisterButton();
    }

    @Then("Alert text should be contains {string}")
    public void alert_text_should_be_contains(String alertText) {
        assertTrue(registerPage.getAlertText().contains(alertText));
    }
}
