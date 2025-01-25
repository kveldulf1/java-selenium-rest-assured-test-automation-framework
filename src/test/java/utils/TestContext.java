package stepdefs;

import io.restassured.response.Response;

public class TestContext {
    private Response lastResponse;

    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    public Response getLastResponse() {
        return lastResponse;
    }
} 