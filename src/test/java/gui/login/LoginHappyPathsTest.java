package gui.login;

import com.n3qa.POM.HomePage;
import com.n3qa.POM.LoginPage;
import gui.base.BaseTest;

import org.testng.annotations.Test;

public class LoginHappyPathsTest extends BaseTest {

    @Test
    public void check() throws InterruptedException {

        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");


        homePage.openHomePage();
        homePage.navigateToLoginPageViaClickOnNavigationLoginButton();

        LoginPage loginPage = new LoginPage(driver,log);
        loginPage.provideCredentilas();

        Thread.sleep(4444);


    }
   //1. Login with already registered user - valid credentials

   //2. Login with newly created/registered user - valid credentials


}
