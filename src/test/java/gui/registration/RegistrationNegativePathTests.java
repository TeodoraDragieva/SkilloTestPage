package gui.registration;

import com.td.POM.HomePage;
import com.td.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

import static com.td.POM.HomePage.HOME_PAGE_URL;
import static com.td.POM.RegistrationPage.REGISTRATION_FORM_TITLE;
import static com.td.POM.RegistrationPage.REGISTRATION_PAGE_URL;

public class RegistrationNegativePathTests extends BaseTest {

    @Test (priority = 0)
    public void verifyTheUserCannotRegisterWithTakenUsername() throws InterruptedException {

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        log.info("STEP 1: The Unregistered User is landing on the Registration Page.");
        registrationPage.navigateToRegistrationPage();

        log.info("STEP 1.1.: Verify the user is on the Registration Page.");
        HomePage homePage = new HomePage(super.driver,log);
        boolean isRegistrationPageLoaded = registrationPage.isUrlLoaded(REGISTRATION_PAGE_URL);
        Assert.assertTrue(isRegistrationPageLoaded, "The Registration Page is Not loaded!");

        log.info("STEP 1.1.1.: Verify the Registration Form Title is presented.");
        String actualRegFormTitle = registrationPage.getRegPageFormTitle();
        Assert.assertEquals(actualRegFormTitle, REGISTRATION_FORM_TITLE);

        log.info("STEP 2.: Provide Already Taken username.");
        registrationPage.provideUserName("72011Student");

        log.info("STEP 3.: Provide not taken email.");
        registrationPage.provideEmail("test999@email.com");

        log.info("STEP 4.: Provide Date of Birth.");
        registrationPage.provideDateOfBirth("01/01/1990");

        log.info("STEP 5.: Provide Password for Registration.");
        registrationPage.providePasswordForRegistrationForm("123456A");

        log.info("STEP 6.: Confirm Password for Registration.");
        registrationPage.provideSamePasswordForRegistrationForm("123456A");

        log.info("STEP 7.:Provide Public Info");
        registrationPage.providePublicInfo("Test Public Info.");

        log.info("STEP 7.: Submit the Sing In Button.");
        registrationPage.clickOnSignInButton();

        log.info("STEP 8.: Verify the Registration Was Not successful.");

        log.info("STEP 8.1.: Verify the Message Username taken is presented.");
        String actualRegistrationMessage = registrationPage.getRegistrationActionMessage();
        Assert.assertEquals(actualRegistrationMessage, "Username taken");

        log.info("STEP 8.2.: Verify the user is still on the Registration Page.");
        boolean isRegistrationPageStillLoaded = registrationPage.isUrlLoaded(REGISTRATION_PAGE_URL);
        Assert.assertTrue(isRegistrationPageStillLoaded, "The User is not on Registration Page!");

        log.info("STEP 8.3.: Verify the Registration Form Title is still presented.");
        String checkAgainActualRegFormTitle = registrationPage.getRegPageFormTitle();
        Assert.assertEquals(checkAgainActualRegFormTitle, REGISTRATION_FORM_TITLE);

        log.info ("STEP 8.4.: Verify the Sing in Button is still visible");
        boolean isLoginButtonStillVisile = registrationPage.isSignInButtonShown();
        Assert.assertTrue(isLoginButtonStillVisile);

    }

    @Test (priority = 1)
    public void verifyTheUserCannotRegisterWithTakenEmail() throws InterruptedException {

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        log.info("STEP 1: Unregistered User is landing on the Registration Page.");
        registrationPage.navigateToRegistrationPage();

        log.info("STEP 1.1.: Verify the User is on the Registration Page.");
        HomePage homePage = new HomePage(super.driver,log);
        boolean isRegistrationPageLoaded = homePage.isUrlLoaded(REGISTRATION_PAGE_URL);
        Assert.assertTrue(isRegistrationPageLoaded);

        log.info("STEP 1.1.1.: Verify the Registration Form Title is presented.");
        String actualRegFormTitle = registrationPage.getRegPageFormTitle();
        Assert.assertEquals(actualRegFormTitle, REGISTRATION_FORM_TITLE);

        log.info("STEP 2.: Provide Not taken User Name.");
        registrationPage.provideUserName("nqmAtakavuser12");

        log.info("STEP 3.: Provide already taken email.");
        registrationPage.provideEmail("tedi90@abv.bg");

        log.info("STEP 4.: Provide Date of Birth.");
        registrationPage.provideDateOfBirth("01/01/1990");

        log.info("STEP 5.: Provide Password.");
        registrationPage.providePasswordForRegistrationForm("123456A");

        log.info("STEP 6.: Confirm Password.");
        registrationPage.provideSamePasswordForRegistrationForm("123456A");

        log.info("STEP 7.: Provide Public Info");
        registrationPage.providePublicInfo("Test Public Info.");

        log.info("STEP 7.: Submit the Sing In Button.");
        registrationPage.clickOnSignInButton();

        log.info("STEP 8.: Verify the Registration Was Not successful.");

        log.info("STEP 8.1.: Verify the Message for Already Taken User is presented");
        String actualRegistrationMessage = registrationPage.getRegistrationActionMessage();
        Assert.assertEquals(actualRegistrationMessage, "Email taken");

        log.info("STEP 8.2.: Verify the user is still on the Registration Page.");
        boolean isHomePageStillLoaded = homePage.isUrlLoaded(REGISTRATION_PAGE_URL);
        Assert.assertTrue(isHomePageStillLoaded);

        log.info("STEP 8.3.: Verify the Registration Form Title is still presented.");
        String checkAgainActualRegFormTitle = registrationPage.getRegPageFormTitle();
        Assert.assertEquals(checkAgainActualRegFormTitle, REGISTRATION_FORM_TITLE);

        log.info ("STEP 8.4.: Verify the Sing in Button is stilL visible");
        boolean isLoginButtonStillVisible = registrationPage.isSignInButtonShown();
        Assert.assertTrue(isLoginButtonStillVisible);
    }
}
