package gui.registration;

import com.td.POM.HomePage;
import com.td.POM.LoginPage;
import com.td.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

import static com.td.POM.RegistrationPage.REGISTRATION_FORM_TITLE;
import static com.td.POM.RegistrationPage.REGISTRATION_PAGE_URL;


public class RegistrationHappyPath extends BaseTest {

    private static final String HOME_PAGE_URL = "/posts/all";

    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {

        String USERNAME = RegistrationDataGenerator.createUser();
        String EMAIL = RegistrationDataGenerator.createEmail();

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        log.info("STEP 1: The Unregistered User is landing on the Registration Page");
        registrationPage.navigateToRegistrationPage();

        log.info("STEP 1.1.: Verify the user is on the Registration Page");
        HomePage homePage = new HomePage(super.driver,log);
        boolean isHomePageLoaded = homePage.isUrlLoaded(REGISTRATION_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded);

        log.info("STEP 1.1.1.: Verify the Registration Form Title is presented");
        String actualRegFormTitle = registrationPage.getRegPageFormTitle();
        Assert.assertEquals(actualRegFormTitle, REGISTRATION_FORM_TITLE);

        log.info("STEP 2.: Provide Random User Name");
        registrationPage.provideUserName(USERNAME);

        log.info("STEP 3.: Provide Random email");
        registrationPage.provideEmail(EMAIL);

        log.info("STEP 4.: Provide Date of Birth.");
        registrationPage.provideDateOfBirth("01/01/1990");

        log.info("STEP 5.: Provide Password.");
        registrationPage.providePasswordForRegistrationForm("123456A");

        log.info("STEP 6.: Provide same Password.");
        registrationPage.provideSamePasswordForRegistrationForm("123456A");

        log.info("STEP 7.: Provide Public Info");
        registrationPage.providePublicInfo("Test Public Info");

        log.info("STEP 8.: Click on Sign In button.");
        boolean isSignInButtonPresented = registrationPage.isSignInButtonShown();
        Assert.assertTrue(isSignInButtonPresented);
        registrationPage.clickOnSignInButton();

        log.info("STEP 9.: Verify the Registration is successful");

        log.info("STEP 9.1.: Verify the Message for Successful Registration");
        String actualRegistrationMessage = registrationPage.getRegistrationActionMessage();
        Assert.assertEquals(actualRegistrationMessage, "Successful register!");

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 9.2.: Verify the Log out Button is presented");
        boolean isShownLogoutButton = loginPage.isLogoutButtonShown();
        Assert.assertTrue(isShownLogoutButton, "The Logout Button is not presented!");

        log.info("STEP 9.3.: Verify the Navigation bar Profile Link is presented");
        boolean isNavBarProfileShown = homePage.isNavBarProfilePresented();
        Assert.assertTrue(isNavBarProfileShown, "The Navigation Bar Profile Link is not presented!");

        log.info("STEP 9.4.: Verify the current URL is for Home Page.");
        boolean isHomePageLoadedAgain = homePage.isUrlLoaded(HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoadedAgain,"The Home page is not loaded!");

    }
    }

