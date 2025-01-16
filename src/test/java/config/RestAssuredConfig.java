package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * RestAssured configuration for all API tests
 */
public class RestAssuredConfig {
    private static final Object LOCK = new Object();
    private static volatile RequestSpecification requestSpec;
    private static volatile ResponseSpecification responseSpec;
    
    /**
     * Initializes basic RestAssured configuration
     */
    public static void setup() {
        synchronized (LOCK) {
            // Request configuration
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri("http://localhost:3000/api")
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .build();
                    
            // Response configuration
            responseSpec = new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .build();
        }
    }
    
    /**
     * Returns request specification, initializing it if needed
     */
    public static RequestSpecification getRequestSpec() {
        synchronized (LOCK) {
            if (requestSpec == null) setup();
            return requestSpec;
        }
    }
    
    /**
     * Returns response specification, initializing it if needed
     */
    public static ResponseSpecification getResponseSpec() {
        synchronized (LOCK) {
            if (responseSpec == null) setup();
            return responseSpec;
        }
    }
    
    /**
     * Updates request specification by adding authorization token
     * @param token Token to add in Authorization header
     */
    public static void updateRequestSpecWithToken(String token) {
        synchronized (LOCK) {
            requestSpec = new RequestSpecBuilder()
                    .addRequestSpecification(requestSpec)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }
    }
} 