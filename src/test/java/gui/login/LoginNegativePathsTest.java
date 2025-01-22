package gui.login;

import com.td.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.td.POM.LoginPage.LOGIN_FORM_TITLE;
import static com.td.POM.LoginPage.LOGIN_PAGE;

public class LoginNegativePathsTest extends BaseTest {

    @Test
    public void verifyUserCannotLoginWithWrongUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1: Already registered user is landing on Iskilo Login Page");
        loginPage.navigateToLoginPage();

        log.info("STEP 1.1.Verify the user is on the Login page");
        boolean isTitleCorrect = loginPage.isPageTitleCorrect();
        Assert.assertTrue(isTitleCorrect, "Page title is incorrect!");

        log.info("STEP 1.1.2. Verify that the Login Form is presented");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle, LOGIN_FORM_TITLE);

        log.info("STEP 3. Provide username");
        loginPage.provideUserName("WRONG");

        log.info("STEP 4. Provide password");
        loginPage.providePassword("testing");

        log.info("STEP 5. Click on login Button");
        loginPage.clickOnLoginButton();

        log.info("STEP 6.1. Verify the Login is successful");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG, "Wrong username or password!");

        log.info("STEP 6.1.2. Verify that the Login Form is presented");
        String actualLoginFormTitle2 = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle2, LOGIN_FORM_TITLE);

        log.info("STEP 6.1.3. Verify that the Login Button From Login Form is presented");
        boolean isLoginButtonStayVisbile = loginPage.loginButtonIsShown();
        Assert.assertTrue(isLoginButtonStayVisbile, "The login Button is not presented!");

        log.info("STEP 6.1.3. Verify that the User is still on the Login Page.");
        boolean isLoginPageStillLoaded = loginPage.isUrlLoaded(LOGIN_PAGE);
        Assert.assertTrue(isLoginPageStillLoaded, "The Login Page is not loaded!");
    }
}
