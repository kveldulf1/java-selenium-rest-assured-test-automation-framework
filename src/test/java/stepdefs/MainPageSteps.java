package stepdefs;

import io.cucumber.java.en.*;
import pageobjects.MainPage;

public class MainPageSteps extends BaseSteps {

    private MainPage mainPage;

    public MainPageSteps() {
        this.mainPage = new MainPage(driver);
    }

    @Given("I am on the main page")
    public void i_am_on_main_page() {
        mainPage.go();
    }
}