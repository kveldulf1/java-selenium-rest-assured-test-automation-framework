import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import helpers.LoggerManager;

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

    // Indicates if the global setup has been completed; shared across all instances
    private static volatile boolean globalSetupDone = false;

    // Lock object for ensuring thread-safe global setup
    private static final Object SETUP_LOCK = new Object();

    @BeforeAll
    public static void globalSetup() {
        // It uses SETUP_LOCK as the lock object to prevent concurrent execution. It
        // uses SETUP_LOCK as the lock object to prevent concurrent execution.
        synchronized (SETUP_LOCK) {
            // Checks if the global setup has already been completed. If not, it proceeds
            // with the setup tasks.
            if (!globalSetupDone) {
                // Adds a key-value pair to the Mapped Diagnostic Context (MDC), which is used
                // for logging. It helps identify logs related to the global setup.
                MDC.put("testName", "GlobalSetup");
                log.info("Loading test configuration");
                log.info("Cleaning database");
                given()
                        .when()
                        .get("http://localhost:3000/api/restoreDB")
                        .then()
                        .statusCode(201);
                LoggerManager.printGlobalLogs();
                globalSetupDone = true;
            }
        }
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        // Adds a key-value pair to the Mapped Diagnostic Context (MDC), which is used
        // for logging. It helps identify logs related to the current test. 
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
        // Prints the logs for the current test. This implementation is due to the
        // parallel execution of tests.
        System.out.println(LoggerManager.getAndClearLogs(testInfo.getDisplayName()));
        // Clears the MDC for the current thread, removing the testName key-value pair.
        MDC.clear();
    }
}
