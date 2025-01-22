package stepdefs;

import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseApiSteps {
    protected RequestSpecification requestSpec;
    protected Response response;

    public BaseApiSteps() {
        requestSpec = given();
        RestAssuredConfig.setup();
    }
}
