package stepdefs;

import io.cucumber.java.en.Given;
import utils.CommonApiCalls;

public class CommonApiSteps extends BaseApiSteps {

    private final CommonApiCalls commonApiCalls;

    public CommonApiSteps() {
        this.commonApiCalls = new CommonApiCalls();
    }


}
