import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import helpers.BufferedLogAppender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import ch.qos.logback.classic.Logger;
import static io.restassured.RestAssured.given;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


public class BaseTest {
    protected WebDriver driver;
    private static final ConfigurationReader configuration = ConfigurationReader.getInstance();
    private static final Logger log = (Logger) LoggerFactory.getLogger(BaseTest.class);
    private static final BufferedLogAppender bufferedLogAppender = new BufferedLogAppender();

    static {
        bufferedLogAppender.start();
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).addAppender(bufferedLogAppender);
    }

    @BeforeAll
    public static void loadConfiguration() {
        log.info("Loading test configuration");
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        MDC.put("testName", testInfo.getDisplayName());
        log.info("Starting test: {}", testInfo.getDisplayName());
        log.debug("Starting browser setup");
        try {
            driver = new BrowserFactory().createInstance(configuration);
            log.debug("Browser started successfully");
        } catch (NoSuchBrowserException e) {
            log.error("Failed to start browser", e);
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        log.info("Finished test: {}", testInfo.getDisplayName());
        log.debug("Closing browser");
        driver.quit();
        System.out.println(bufferedLogAppender.getAndClearLogs(testInfo.getDisplayName()));
        MDC.clear();
    }

    @BeforeAll
    public static void cleanDatabase() {
        log.info("Cleaning database");
        given().when().get("http://localhost:3000/api/restoreDB").then().statusCode(201);
    }
}
