package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import config.RestAssuredConfig;

import static io.restassured.RestAssured.given;

public abstract class BaseService {
    protected final RequestSpecification requestSpec;

    protected BaseService() {
        this.requestSpec = RestAssuredConfig.getRequestSpec();
    }

    protected Response post(String endpoint, Object body) {
        return given()
            .spec(requestSpec)
            .body(body)
            .when()
            .post(endpoint);
    }

    protected Response get(String endpoint) {
        return given()
            .spec(requestSpec)
            .when()
            .get(endpoint);
    }

    protected Response put(String endpoint, Object body) {
        return given()
            .spec(requestSpec)
            .body(body)
            .when()
            .put(endpoint);
    }

    protected Response delete(String endpoint) {
        return given()
            .spec(requestSpec)
            .when()
            .delete(endpoint);
    }
}
