package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;

import org.openqa.selenium.WebDriver;
import static io.restassured.RestAssured.given;

public class AppHooks {
    private static ConfigurationReader configuration;
    private static WebDriver driver;
    
    @BeforeAll
    public static void loadConfiguration() {
        configuration = new ConfigurationReader();
    }
    
    @Before
    public void setup() {
        BrowserFactory browser = new BrowserFactory();
        try {
            driver = browser.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
        
        // Database cleanup
        given()
            .when()
            .get("http://localhost:3000/api/restoreDB")
            .then()
            .statusCode(201);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public static WebDriver getDriver() {
        return driver;
    }
} 