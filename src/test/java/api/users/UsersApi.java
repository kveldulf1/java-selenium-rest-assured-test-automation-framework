package api.users;

import config.RestAssuredConfig;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import pojo.users.CreateUserRequest;

import static io.restassured.RestAssured.given;


public class UsersApi {

    public static Response createUser(CreateUserRequest createUserRequest) {
        return given()
            .spec(RestAssuredConfig.getRequestSpec())
            .body(createUserRequest)
            .when()
            .post(ApiEndpoints.CREATE_USER);
    }

}
