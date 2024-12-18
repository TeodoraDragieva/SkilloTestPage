package gui.login;

import com.n3qa.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.annotations.Test;

public class LoginNegativePathsTest extends BaseTest {

    @Test
    public void verifyUserCannotLoginWithWrongUserName(){
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 1: Already registered user is landing Iskilo loinpage");
        loginPage.navigateToLoginPage();
        //Verification that the user is on login page
        //Verification login form is presented

        loginPage.provideUserName("WRONG");

        loginPage.providePassword("testing");


        loginPage.clickOnLoginButton();
        //Assertion
        //  Login action message shows = ERROR text
        //If user cannot login - login button states the some =  Form visible
        // Login button from login Form visible
        // page ULR = some
    }

    @Test
    public void verifyUserCannotLoginWithEmptyUserName(){
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 1: Already registered user is landing Iskilo loinpage");
        loginPage.navigateToLoginPage();
        //Verification that the user is on login page
        //Verification login form is presented

        loginPage.provideUserName(" ");

        loginPage.providePassword("testing");


        loginPage.clickOnLoginButton();
        //Assertion
        //  Login action message shows = ERROR text
        //If user cannot login - login button states the some =  Form visible
        // Login button from login Form visible
        // page ULR = some
    }

    @Test
    public void verifyUserCannotLoginWithWrongPassword(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
        loginPage.provideUserName("testingDemo");
        loginPage.providePassword("WrongPassword");

    }

    @Test
    public void verifyUserCannotLoginWithEmptyPassword(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
        loginPage.provideUserName("testingDemo");
        loginPage.providePassword(" ");

    }

    @Test
    public void verifyUserCannotLoginWithEmptyCredentialsData(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
    }


}
