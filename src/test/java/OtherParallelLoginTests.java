import org.junit.jupiter.api.Test;
import pageobjects.MainPage;
import pageobjects.WelcomePage;
import org.junit.jupiter.api.Assertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

/**
 * This class demonstrates parallel test execution capabilities and thread-safe test data handling.
 * Multiple test instances run concurrently while safely managing shared test data through resource locking,
 * preventing race conditions when accessing the same user credentials pool.
 */
@Epic("Authentication")
@Feature("Parallel Login Extended")
public class OtherParallelLoginTests extends BaseTest {

        @Test
        @Story("Parallel Login")
        @Description("Verify concurrent login - additional test instance 4")
        @Severity(SeverityLevel.CRITICAL)
        public void userLogsIn4() {
                WelcomePage welcomePage = new MainPage(driver)
                                .go()
                                .headerComponent
                                .hoverMouseOverUserIcon()
                                .clickLoginButton()
                                .loginAsRandomExistingUser();

                Assertions.assertTrue(welcomePage.isUrlCorrect(),
                                "Url is not correct after login. Current url: " + driver.getCurrentUrl());
        }

        @Test
        @Story("Parallel Login")
        @Description("Verify concurrent login - additional test instance 5")
        @Severity(SeverityLevel.CRITICAL)
        public void userLogsIn5() {
                WelcomePage welcomePage = new MainPage(driver)
                                .go()
                                .headerComponent
                                .hoverMouseOverUserIcon()
                                .clickLoginButton()
                                .loginAsRandomExistingUser();

                Assertions.assertTrue(welcomePage.isUrlCorrect(),
                                "Url is not correct after login. Current url: " + driver.getCurrentUrl());
        }

        @Test
        @Story("Parallel Login")
        @Description("Verify concurrent login - additional test instance 6")
        @Severity(SeverityLevel.CRITICAL)
        public void userLogsIn6() {
                WelcomePage welcomePage = new MainPage(driver)
                                .go()
                                .headerComponent
                                .hoverMouseOverUserIcon()
                                .clickLoginButton()
                                .loginAsRandomExistingUser();

                Assertions.assertTrue(welcomePage.isUrlCorrect(),
                                "Url is not correct after login. Current url: " + driver.getCurrentUrl());
        }
}