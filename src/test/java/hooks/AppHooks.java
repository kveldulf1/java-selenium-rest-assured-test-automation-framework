package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.openqa.selenium.WebDriver;
import config.RestAssuredConfig;

import static io.restassured.RestAssured.given;

public class AppHooks {
    private static ConfigurationReader configuration;
    private static WebDriver driver;
    
    @BeforeAll
    public static void globalSetup() {
        configuration = new ConfigurationReader();
        RestAssuredConfig.setup();
    }
    
    @Before("@Api")
    public void apiSetup() {
        // Database cleanup for API tests
        given()
            .spec(RestAssuredConfig.getRequestSpec())
            .when()
            .get("/restoreDB")
            .then()
            .statusCode(201);
    }
    
    @Before("not @Api")
    public void uiSetup() {
        BrowserFactory browser = new BrowserFactory();
        try {
            driver = browser.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
    }
    
    @After("not @Api")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public static WebDriver getDriver() {
        return driver;
    }
}