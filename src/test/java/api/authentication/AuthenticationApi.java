package api.authentication;

import io.restassured.response.Response;
import pojo.authentication.LoginRequest;
import constants.ApiEndpoints;
import config.RestAssuredConfig;

import static io.restassured.RestAssured.given;

public class AuthenticationApi {
    
    public static Response login(LoginRequest loginRequest) {
        return given()
            .spec(RestAssuredConfig.getRequestSpec())
            .body(loginRequest)
            .when()
            .post(ApiEndpoints.LOGIN);
    }
} 