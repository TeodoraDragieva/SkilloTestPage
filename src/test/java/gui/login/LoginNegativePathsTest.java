package gui.login;

import com.td.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.td.POM.LoginPage.LOGIN_FORM_TITLE;
import static com.td.POM.LoginPage.LOGIN_PAGE;

public class LoginNegativePathsTest extends BaseTest {
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";

    @Test
    public void verifyUserCannotLoginWithWrongUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 1: Already registered user is landing on Iskilo Login Page");
        loginPage.navigateToLoginPage();

        log.info("STEP 1.1.Verify the user is on the Login page");
        boolean isTitleCorrect = loginPage.isPageTitleCorrect();
        Assert.assertTrue(isTitleCorrect, "Page title is incorrect!");

        log.info("STEP 1.1.2. Verify that the Login Form is presented");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3. Provide username");
        loginPage.provideUserName("WRONG");

        log.info("STEP 4. Provide password");
        loginPage.providePassword("testing");

        log.info("STEP 5. Click on login Button");
        loginPage.clickOnLoginButton();

        log.info("STEP 6.1. Verify the Login is successful");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG,LOGIN_NOT_SUCCESSFUL_MSG);

        log.info("STEP 6.1.2. Verify that the Login Form is presented");
        String actualLoginFormTitle2 = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle2,LOGIN_FORM_TITLE);

        log.info("STEP 6.1.3. Verify that the Login Button From Login Form is presented");
        boolean isLoginButtonStayVisile = loginPage.loginButtonIsShown();
        Assert.assertTrue(isLoginButtonStayVisile);

        log.info("STEP 6.1.3. Verify that the User is still on the Login Page.");
        boolean isLoginPageStillLoaded = loginPage.isURLLoaded(LOGIN_PAGE);
        Assert.assertTrue(isLoginPageStillLoaded);
    }
//
//    @Test
//    public void verifyUserCannotLoginWithEmptyUserName(){
//        LoginPage loginPage = new LoginPage(super.driver,log);
//
//        log.info("STEP 1: Already registered user is landing Iskilo loinpage");
//        loginPage.navigateToLoginPage();
//        //Verification that the user is on login page
//        //Verification login form is presented
//
//        loginPage.provideUserName(" ");
//
//        loginPage.providePassword("testing");
//
//
//        loginPage.clickOnLoginButton();
//        //Assertion
//        //  Login action message shows = ERROR text
//        //If user cannot login - login button states the some =  Form visible
//        // Login button from login Form visible
//        // page ULR = some
//    }

//    @Test
//    public void verifyUserCannotLoginWithWrongPassword(){
//        LoginPage loginPage = new LoginPage(super.driver,log);
//        loginPage.navigateToLoginPage();
//        loginPage.provideUserName("testingDemo");
//        loginPage.providePassword("WrongPassword");
//
//    }

//    @Test
//    public void verifyUserCannotLoginWithEmptyPassword(){
//        LoginPage loginPage = new LoginPage(super.driver,log);
//        loginPage.navigateToLoginPage();
//        loginPage.provideUserName("testingDemo");
//        loginPage.providePassword(" ");
//
//    }

//    @Test
//    public void verifyUserCannotLoginWithEmptyCredentialsData(){
//        LoginPage loginPage = new LoginPage(super.driver,log);
//        loginPage.navigateToLoginPage();

    }

