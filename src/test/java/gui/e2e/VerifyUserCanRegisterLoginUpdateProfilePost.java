package gui.e2e;

import com.td.POM.*;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

import java.io.File;

import static com.td.POM.RegistrationPage.REGISTRATION_FORM_TITLE;
import static com.td.POM.RegistrationPage.REGISTRATION_PAGE_URL;

public class VerifyUserCanRegisterLoginUpdateProfilePost extends BaseTest {

    private static final String HOME_PAGE_URL = "/posts/all";
    File postPicture = new File("src/test/resources/upload/pirin.jpg");

    @Test
    public void verifyTheUserCanRegisterLoginWithValidCredentialsAndCreateAPost() throws InterruptedException {

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

        loginPage.clickOnLogOutButton();

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
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,"Sign in");

        log.info("STEP 2.2. Verify that the Login Button From Login Form is presented");
        boolean isLoginButtonVisbile = loginPage.loginButtonIsShown();
        Assert.assertTrue(isLoginButtonVisbile, "The login Button is not presented!");

        log.info("STEP 11.: Log in with the saved username and password.");
        loginPage.provideUserName(USERNAME);
        loginPage.providePassword("123456A");
        loginPage.clickOnLoginButton();

        boolean isLoginSuccessful = loginPage.isLogoutButtonShown();
        Assert.assertTrue(isLoginSuccessful, "Login was not successful!");

        log.info ("STEP 5.: Verify the navigation bare Profile link is presented and click on it.");
        homePage.clickOnNavBarProfile();

        log.info("STEP 6.: Verify the User is navigated to the Profile Page");

        log.info("STEP 6.1.: Verify if the URL is for the Profile Page");
        ProfilePage profilePage = new ProfilePage(super.driver,log);

        log.info("STEP 7.: Verify there is a post - to be deleted.");
        int initialPostCount = profilePage.countAllPostsWithScroll();

        log.info ("STEP 8.: The User click on navigation bar New Post link.");
        homePage.clickOnNavBarNewPost();

        log.info ("STEP 9.: Verify the User is on Post Page.");
        PostPage postPage = new PostPage(super.driver, log);

        log.info("STEP 9.1.: Verify that the Upload Form is presented");
        String actualUploadFormTitle = postPage.getUploadPageFormTitle();
        Assert.assertEquals(actualUploadFormTitle,"Post a picture to share with your awesome followers");

        log.info ("STEP 10.: The User is Uploading New Picture.");
        postPage.uploadPicture(postPicture);

        log.info ("STEP 11.: The User is providing Post Caption.");
        postPage.providePostCaption("testing");
        postPage.clickCreatePostButton();

        log.info ("STEP 12.: Verify the Post is created.");

        log.info ("STEP 12.1.: Click on Post.");
        int lastPostIndex = profilePage.getLastPostIndex();
        profilePage.clickPost(lastPostIndex);

        log.info ("STEP 12.2.: Verify the Image is visible in the Post Modal.");
        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        log.info("STEP 12.3.: Verify the Image name (caption) is visible in the Post Modal.");
        String postCaption = postPage.getImageName();
        Assert.assertEquals(postCaption, "testing", "The caption is not presented correctly.");

        log.info ("STEP 12.4.: Verify the Username is visible in the new post.");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, USERNAME);

        profilePage.closePostModal();

        log.info ("STEP 12.5.: Verify there is one more post shown.");
        int postFinalCount = profilePage.countAllPostsWithScroll ();
        Assert.assertTrue(postFinalCount == initialPostCount + 1, "Posts didn't increase by 1.");

    }

}

