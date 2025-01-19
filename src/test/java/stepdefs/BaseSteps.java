package stepdefs;

import org.openqa.selenium.WebDriver;
import hooks.AppHooks;

public class BaseSteps {
    protected WebDriver driver;
    
    public BaseSteps() {
        this.driver = AppHooks.getDriver();
    }
} 