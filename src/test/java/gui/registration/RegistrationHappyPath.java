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

    public static final String REGISTRATION_SUCCESSFUL_MSG = "Successful register!";
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
        boolean isHomePageLoaded = homePage.isURLLoaded(REGISTRATION_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded);

        log.info("STEP 1.1.1.: Verify the Registration Form Title is presented");
        String actualRegFormTitle = registrationPage.getRegPageFormTitle();
        Assert.assertEquals(actualRegFormTitle, REGISTRATION_FORM_TITLE);

        log.info("STEP 2.: Provide Random User Name");
        registrationPage.provideUserName(USERNAME);

        log.info("STEP 3.: Provide Random email");
        registrationPage.provideEmail(EMAIL);

        log.info("STEP 4.: Provide Date of Birth");
        registrationPage.provideDateOfBirth("01/01/1990");

        log.info("STEP 5.: Provide Date of Birth");
        registrationPage.providePasswordForRegistrationForm("123456A");

        log.info("STEP 6.:Provide Date of Birth");
        registrationPage.provideSamePasswordForRegistrationForm("123456A");

        log.info("STEP 7.: Provide Public Info");
        registrationPage.providePublicInfo("Test Public Info");
        registrationPage.clickOnSignInButton();

        log.info("STEP 6.: Verify the Registration is successful");

        log.info("STEP 6.1.: Verify the Message for Successful Registration");
        String actualRegistrationMessage = registrationPage.getRegistrationActionMessage();
        Assert.assertEquals(actualRegistrationMessage, REGISTRATION_SUCCESSFUL_MSG);

        LoginPage loginPage = new LoginPage(super.driver, log);
        homePage = new HomePage(super.driver, log);

        log.info("STEP 6.2.: Verify the Log out Button is presented");
        boolean isShownLogoutButton = loginPage.isLogoutButtonShown();
        Assert.assertTrue(isShownLogoutButton);

        log.info("STEP 6.3.: Verify the Navigation bar Profile Link is presented");
        boolean isNavBarProfileShown = homePage.isNavBarProfilePresented();
        Assert.assertTrue(isNavBarProfileShown);

        log.info("STEP 6.4.: Verify the current URL is for Home Page.");
        boolean isHomePageLoadedAgain = homePage.isURLLoaded(HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoadedAgain);

    }
    }

