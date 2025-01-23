package config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

/**
 * RestAssured configuration for all API tests
 */
public class RestAssuredConfig {
    private static final Logger log = new LoggerContext().getLogger(RestAssuredConfig.class);
    protected static final String BASE_URI = "http://localhost:3000/api";
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    
    /**
     * Initializes basic RestAssured configuration
     */
    public static void setup() {
        log.info("Starting API configuration setup");
        configureSpecifications();
        configureLogging();
        log.info("API configuration setup completed");
    }
    
    private static void configureSpecifications() {     
        // 1. Configure main request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
                
        // 2. Response configuration
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

        // 3. Configure RestAssured defaults
        requestSpecification = requestSpec;
        responseSpecification = responseSpec;
    }
    
    private static void configureLogging() {
        filters(new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.ALL));
    }
    
    /**
     * Returns request specification, initializing it if needed
     */
    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            // Create basic spec without triggering setup()
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }
    
    /**
     * Returns response specification, initializing it if needed
     */
    public static ResponseSpecification getResponseSpec() {
        if (responseSpec == null) setup();
        return responseSpec;
    }
    
    /**
     * Updates request specification by adding authorization token
     * @param token Token to add in Authorization header
     */
    public static void updateRequestSpecWithToken(String token) {
        requestSpec = new RequestSpecBuilder()
                .addRequestSpecification(requestSpec)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
} 