package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import com.google.gson.JsonObject;
import helpers.UserTestData;

public class RegisterPage extends BasePage {

    private static final String EXPECTED_URL = "http://localhost:3000/register";
    private By usernameInput = By.cssSelector("input[data-testid='firstname-input']");
    private By lastnameInput = By.cssSelector("input[data-testid='lastname-input']");
    private By emailInput = By.cssSelector("input[data-testid='email-input']");
    private By passwordInput = By.cssSelector("input[data-testid='password-input']");
    private By registerButton = By.cssSelector("input[data-testid='register-button']");
    private By alertPopup = By.cssSelector("div[data-testid='alert-popup']");
    
    private final JsonObject testUser;

    public RegisterPage(WebDriver driver) {
        super(driver);
        testUser = UserTestData.getDefaultTestUser();
        log.debug("Loaded test user data for registration");
    }

    private boolean isUrlCorrect() {
        boolean result = driver.getCurrentUrl().equals(EXPECTED_URL);
        log.debug("URL check: expected '{}', actual '{}', match: {}",
                EXPECTED_URL, driver.getCurrentUrl(), result);
        return result;
    }

    public void typeUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        driver.findElement(usernameInput).sendKeys(username);
        log.debug("Typed username: {}", username);
    }

    public void typeLastname(String lastname) {
        wait.until(ExpectedConditions.elementToBeClickable(lastnameInput));
        driver.findElement(lastnameInput).sendKeys(lastname);
        log.debug("Typed lastname: {}", lastname);
    }

    public void typeEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        driver.findElement(emailInput).sendKeys(email);
        log.debug("Typed email: {}", email);
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
        log.debug("Typed password: {}", "*".repeat(password.length()));
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
        log.debug("Clicked register button");
    }

    public WelcomePage provideUserDetailsAndRegister() {
        log.info("Starting registration process");
        isUrlCorrect();
        typeUsername(testUser.get("username").getAsString());
        typeLastname(testUser.get("lastname").getAsString());
        typeEmail(testUser.get("email").getAsString());
        typePassword(testUser.get("password").getAsString());
        clickRegisterButton();

        if (getAlertText().contains("User not created! Email not unique")) {
            log.error("Registration failed: Email is not unique");
        } else if (isSuccessfullyRegistered()) {
            log.info("Registration completed");
        }
        return new WelcomePage(driver);
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertPopup));
        return driver.findElement(alertPopup).getText();
    }

    private boolean isSuccessfullyRegistered() {
        return getAlertText().contains("User created");
    }
}