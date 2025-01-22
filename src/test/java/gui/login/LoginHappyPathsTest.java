package gui.login;

import com.td.POM.HomePage;
import com.td.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginHappyPathsTest extends BaseTest {

    private static final String HOME_PAGE_URL = "/posts/all";

    @Test
    public void verifyTheUserCanLoginWithValidCredentials() throws InterruptedException {

        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.: Verify the user is on the Home Page.");

        log.info("STEP 1.1.1.: Verify the Page Title.");
        boolean isTitleCorrect = homePage.isPageTitleCorrect();
        Assert.assertTrue(isTitleCorrect, "Page title is incorrect!");

        log.info("STEP 1.1.2.: Verify that the Navigation Bar Home is presented.");
        boolean isShownNavBarHome = homePage.isNavBarHomeLinkShown();
        Assert.assertTrue(isShownNavBarHome);

        log.info("STEP 1.1.3.: Verify that the Login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink, "The Navigation bar Login Link is not presented!");

        log.info("STEP 2: The user is navigating to the Login page via click on navigation bar Login link.");
        homePage.clickOnNavBarLogin();

        log.info("STEP 2.1.: Verify the User successfully landed on the Login Page");
        LoginPage loginPage = new LoginPage(super.driver,log);
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,"Sign in");

        log.info("STEP 2.2. Verify that the Login Button From Login Form is presented");
        boolean isLoginButtonVisbile = loginPage.loginButtonIsShown();
        Assert.assertTrue(isLoginButtonVisbile, "The login Button is not presented!");

        log.info("STEP 3.: Provide username.");
        loginPage.provideUserName("tedi90@abv.bg");

        log.info("STEP 4.: Provide password");
        loginPage.providePassword("Pukanka2");

        log.info("STEP 5. Click on login Button");
        loginPage.clickOnLoginButton();

        log.info("STEP 6.: Verify the Login is successful");

        log.info("STEP 6.1.: Verify the Message for Successful Login");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG,"Successful login!");

        log.info("STEP 6.2.: Verify the Log out Button is presented");
        boolean isShownLogoutButton = loginPage.isLogoutButtonShown();
        Assert.assertTrue(isShownLogoutButton, "The Logout Button is not presented!");

        log.info("STEP 6.3.: Verify the Navigation bar Profile Link is presented");
        boolean isNavBarProfileShown = homePage.isNavBarProfilePresented();
        Assert.assertTrue(isNavBarProfileShown, "The Navigation Bar Profile link is not presented!");

        log.info("STEP 6.4.: Verify the current URL is for Home Page.");
        boolean isHomePageLoaded = homePage.isUrlLoaded(HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded, "The Home Page is not loaded!");
    }
}
