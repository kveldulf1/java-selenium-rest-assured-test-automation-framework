package api;

import io.restassured.response.Response;
import pojo.authentication.LoginRequest;
import constants.ApiEndpoints;

import static io.restassured.RestAssured.given;

public class AuthenticationApi {
    
    public static Response login(LoginRequest loginRequest) {
        return given()
            .body(loginRequest)
            .when()
            .post(ApiEndpoints.LOGIN);
    }
} 