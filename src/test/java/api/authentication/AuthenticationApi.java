package api.authentication;

import io.restassured.response.Response;
import pojo.authentication.LoginRequest;
import constants.ApiEndpoints;
import api.BaseService;

public class AuthenticationApi extends BaseService {
    
    public Response login(LoginRequest loginRequest) {
        return post(ApiEndpoints.LOGIN, loginRequest);
    }
} 