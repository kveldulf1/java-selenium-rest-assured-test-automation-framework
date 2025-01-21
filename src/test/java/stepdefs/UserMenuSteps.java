package stepdefs;

import io.cucumber.java.en.*;
import pageobjects.MainPage;
import pageobjects.UserMenuComponent;

public class UserMenuSteps extends BaseSteps {

    private MainPage mainPage;
    private UserMenuComponent userMenu;

    public UserMenuSteps() {
        this.mainPage = new MainPage(driver);
        this.userMenu = new UserMenuComponent(driver);
    }

    @When("I hover mouse over user icon")
    public void i_hover_mouse_over_user_icon() {
        mainPage.headerComponent.hoverMouseOverUserIcon();
    }

    @When("I click on login button on user menu component")
    public void I_click_on_login_button_on_user_menu_component() {
        userMenu.clickLoginButton();
    }
}
