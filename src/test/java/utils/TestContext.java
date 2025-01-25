package utils;

import io.restassured.response.Response;

/**
 * TestContext class is used to store and share data between cucumber test steps.
 */
public class TestContext {
    // ThreadLocal is used to store the last response in a thread-safe manner.
    private final ThreadLocal<Response> lastResponse = new ThreadLocal<>();

    public void setLastResponse(Response response) {
        lastResponse.set(response);
    }

    public Response getLastResponse() {
        return lastResponse.get();
    }

    // Clear the last response after each test.
    public void clear() {
        lastResponse.remove();
    }
} 