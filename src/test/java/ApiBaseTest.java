import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import static io.restassured.RestAssured.*;
import config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import io.restassured.RestAssured;

public class ApiBaseTest {
    private static final Logger log = new LoggerContext().getLogger(ApiBaseTest.class);
    protected static final String BASE_URI = "http://localhost:3000/api";
    private static volatile boolean databaseCleaned = false;
    private static final Object CLEANUP_LOCK = new Object();

    @BeforeAll
    @ResourceLock(value = "RestAssured", mode = ResourceAccessMode.READ_WRITE)
    public static void setup() {
        log.info("Starting API test setup");
        cleanupDatabaseIfNeeded();
        
        synchronized (CLEANUP_LOCK) {
            // Reset RestAssured to default state
            RestAssured.reset();
            
            // Configure RestAssured
            RestAssuredConfig.setup();
            
            // Set base configs
            RestAssured.baseURI = BASE_URI;
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }
    }

    @BeforeEach
    @ResourceLock(value = "RestAssured", mode = ResourceAccessMode.READ_WRITE)
    public void setupTest() {
        synchronized (CLEANUP_LOCK) {
            // Set request/response specifications for each test
            RestAssured.requestSpecification = RestAssuredConfig.getRequestSpec();
            RestAssured.responseSpecification = RestAssuredConfig.getResponseSpec();

            // Add filters for logging
            RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.ALL)
            );
        }
    }

    private static void cleanupDatabaseIfNeeded() {
        synchronized (CLEANUP_LOCK) {
            if (!databaseCleaned) {
                try {
                    RestAssured.given()
                        .spec(RestAssuredConfig.getRequestSpec())
                        .when()
                        .get("/restoreDB")
                        .then()
                        .statusCode(201);
                    databaseCleaned = true;
                } catch (Exception e) {
                    log.error("Failed to clean database", e);
                    throw new RuntimeException("Test setup failed - couldn't clean database", e);
                }
            }
        }
    }
}
