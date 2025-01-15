package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.gson.JsonObject;

import helpers.TestDataReader;

public class LoginPage extends BasePage {
    private By loginInput = By.cssSelector("input#username");
    private By passwordInput = By.cssSelector("input#password");
    private By loginButton = By.cssSelector("input#loginButton");

    private JsonObject currentUser;

    public LoginPage(WebDriver driver) {
        super(driver);
        log.debug("LoginPage initialized");
    }

    private void typeUsername(String username) {
        log.debug("Typing username: {}", username);
        wait.until(ExpectedConditions.elementToBeClickable(loginInput));
        driver.findElement(loginInput).sendKeys(username);
    }

    private void typePassword(String password) {
        log.debug("Typing password: {}", "*".repeat(password.length()));
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    private void logLoginAttempt(String email) {
        log.info("Performing login with email: {}", email);
    }

    public WelcomePage login(String email, String password) {
        logLoginAttempt(email);
        typeUsername(email);
        typePassword(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
        log.info("Login completed");
        return new WelcomePage(driver);
    }

    public WelcomePage loginAsRandomExistingUser() {
        currentUser = TestDataReader.getRandomValidUser();
        WelcomePage welcomePage = login(
            currentUser.get("email").getAsString(), 
            currentUser.get("password").getAsString()
        );
        TestDataReader.releaseUser(currentUser);
        return welcomePage;
    }

    public LoginPage loginAsInvalidUser() {
        JsonObject invalidUser = TestDataReader.getInvalidUser();
        login(
            invalidUser.get("email").getAsString(), 
            invalidUser.get("password").getAsString()
        );
        return this;
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[data-testid='login-error']")));
        String alertText = driver.findElement(By.cssSelector("p[data-testid='login-error']")).getText();
        log.info("Alert text: {}", alertText);
        return alertText;
    }
}