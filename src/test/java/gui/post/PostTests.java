package gui.post;

import com.td.POM.*;
import com.td.POM.HomePage;
import com.td.POM.ProfilePage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static com.td.POM.LoginPage.LOGIN_FORM_TITLE;
import static com.td.POM.ProfilePage.PROFILE_PAGE;
import static com.td.POM.RegistrationPage.REGISTRATION_FORM_TITLE;
import static com.td.POM.RegistrationPage.REGISTRATION_PAGE_URL;

public class PostTests extends BaseTest {
    public static final String testUser = "testingDemos";
    public static final String testPassword = "testing";
    public static final String caption = "Teddy is Testing the create post caption";
    File postPicture = new File("src/test/resources/upload/pirin.jpg");
    private static final String UPLOAD_FORM_TITLE = "Post a picture to share with your awesome followers";

    @Test(priority = 0)
    public void verifyUserCanCreatePost() throws InterruptedException {

        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.: Verify the user is on the Home Page.");

        log.info("STEP 1.1.1.: Verify the Page Title.");
        boolean isTitleCorrect = homePage.isPageTitleCorrect();
        Assert.assertTrue(isTitleCorrect, "Page title is incorrect!");

        log.info("STEP 1.1.2.: Verify that the Navigation Bar Home is presented.");
        boolean isShownNavBarHome = homePage.isNavBarHomeShown();
        Assert.assertTrue(isShownNavBarHome);

        log.info("STEP 1.1.3.: Verify that the Login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the Login page via click on navigation bar Login link.");
        homePage.clickOnNavBarLogin();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1.: Verify the User is successfully landed on the Login Page.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3.: Provide username and password and clicking on Login Button.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info ("STEP 4.1.: Verify the navigation bare Home link is presented.");
        homePage.isNavBarHomeShown();

        log.info ("STEP 4.2.: The User click on navigation bar New Post link.");
        homePage.clickOnNavBarNewPost();

        PostPage postPage = new PostPage(super.driver, log);
        log.info ("STEP 5.: Verify the User is on Post Page.");

        log.info("STEP 5.1.: Verify that the Upload Form is presented");
        String actualUploadFormTitle = postPage.getUploadPageFormTitle();
        Assert.assertEquals(actualUploadFormTitle,UPLOAD_FORM_TITLE);

        log.info ("STEP 6.: The User is Uploading New Picture.");
        postPage.uploadPicture(postPicture);

        log.info ("STEP 7.: The User is providing Post Caption.");
        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        log.info ("STEP 8.: Verify the Post is created.");

        log.info ("STEP 8.1.: Verify there are more post shown.");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
//        profilePage.verifyThereAreMorePostShown();

        log.info ("STEP 8.2.: Click on Post.");
        profilePage.clickPost(0);

        log.info ("STEP 8.3.: Verify the Image is visibale in the Post Modal.");
        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        log.info ("STEP 8.4.: Verify the text of the new post is visible.");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, testUser);

    }

    @Test (priority = 1)
    public void verifyUserCanLikeOtherMembersPosts() throws InterruptedException {

        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.: Verify the user is on the Home Page.");

        log.info("STEP 1.1.1.: Verify the Page Title.");
        boolean isTitleCorrect = homePage.isPageTitleCorrect();
        Assert.assertTrue(isTitleCorrect, "Page title is incorrect!");

        log.info("STEP 1.1.2.: Verify that the Navigation Bar Home is presented.");
        boolean isShownNavBarHome = homePage.isNavBarHomeShown();
        Assert.assertTrue(isShownNavBarHome);

        log.info("STEP 1.1.3.: Verify that the Login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the Login page via click on navigation bar Login link.");
        homePage.clickOnNavBarLogin();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1.: Verify the User is successfully landed on the Login Page.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3.: Provide username and password and clicking on Login Button.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info ("STEP 4.1.: Verify the navigation bare Home link is presented.");
        homePage.isNavBarHomeShown();

        log.info ("STEP 4.2.: The User click on navigation bar Home link.");
        homePage.clickOnNavBarHome();

        log.info("STEP 5.: The User is navigated to the Public Page");
        ProfilePage profilePage = new ProfilePage(super.driver,log);

        log.info("STEP 6.: The User select Profile of other User - Lora");
        profilePage.ClickOnUserWithUploadedPic();

        log.info("STEP 7.: The User is in the Other User Profile - Lora");

        log.info("STEP 8.:The user has clicked on the first post of the Other User.");
        profilePage.clickPost(0);

        log.info("STEP 9.:The user has clicked on the like button.");
        profilePage.ClickOnLikeButton();

        log.info("STEP 10.:Verify if the Like Message is visible.");
        profilePage.isLikeMessageVisible();

        log.info("STEP 11.:Close the post Modal.");
        profilePage.closePostModal();

    }

    @Test (priority = 2)
    public void verifyUserCanDislikePost() {
        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.: Verify the user is on the Home Page.");

        log.info("STEP 1.1.1.: Verify the Page Title.");
        boolean isTitleCorrect = homePage.isPageTitleCorrect();
        Assert.assertTrue(isTitleCorrect, "Page title is incorrect!");

        log.info("STEP 1.1.2.: Verify that the Navigation Bar Home is presented.");
        boolean isShownNavBarHome = homePage.isNavBarHomeShown();
        Assert.assertTrue(isShownNavBarHome);

        log.info("STEP 1.1.3.: Verify that the Login link is presented.");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the Login page via click on navigation bar Login link.");
        homePage.clickOnNavBarLogin();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1.: Verify the User is successfully landed on the Login Page.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3.: Provide username and password and clicking on Login Button.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info ("STEP 4.1.: Verify the navigation bare Home link is presented.");
        homePage.isNavBarHomeShown();

        log.info ("STEP 4.2.: The User click on navigation bar Home link.");
        homePage.clickOnNavBarHome();

        log.info("STEP 5.: Verify the User is navigated to the Profile Page");

        log.info("STEP 5.: Verify if the URL is for the Profile Page");
        ProfilePage profilePage = new ProfilePage(super.driver,log);
        boolean isProfilePageLoaded = profilePage.isURLLoaded(PROFILE_PAGE);
        Assert.assertTrue(isProfilePageLoaded);

        log.info("STEP 6.: The User select Profile of other User - Lora");
        profilePage.ClickOnUserWithUploadedPic();

        log.info("STEP 7.: The User is in the Other User Profile - Lora");
        //VERIFY - Username Name ???

        log.info("STEP 8.: The user has clicked on the first post of the Other User.");
        profilePage.clickPost(0);

        log.info("STEP 9.: The user click on the Like button in order to dislike post.");
        profilePage.ClickOnLikeButton();

        log.info("STEP 10.: Verify if the Dislike Message is visible.");
        profilePage.isDislikeMessageVisible();

        log.info("STEP 11. Close the post Modal.");
        profilePage.closePostModal();

    }

    @Test (priority = 3)
    public void verifyUserCanDeletePost() throws InterruptedException {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1.: The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("STEP 2.: Verify the User is successfully landed on the Login Page.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3.: Provide username and password and clicking on Login Button.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info ("STEP 4.1.: Verify the navigation bare Home link is presented.");
        homePage.clickOnNavBarProfile();

        //is navigation Bar Profile link Shown.


        log.info("STEP 5.: Verify the User is navigated to the Profile Page");

//        log.info("STEP 5.1.: Verify if the URL is for the Profile Page");
        ProfilePage profilePage = new ProfilePage(super.driver,log);
//        boolean isProfilePageLoaded = profilePage.isURLLoaded(PROFILE_PAGE);
//        Assert.assertTrue(isProfilePageLoaded);

//

        log.info("STEP 6.: Verify there is a post - to be deleted.");

        int initialPostCount = profilePage.getPostCount();
//        boolean areTherePostShown = initialPostCount > 0;
//        Assert.assertTrue(areTherePostShown);
        /////

        log.info("STEP 7.: The user has clicked on the first post.");
        profilePage.clickPost(0);

        log.info("STEP 8.: The user has clicked on the Delete post button and Confirmed they want the post to be deleted.");
        profilePage.deleteWithConfirmationPost();

        log.info("STEP 10.: Verify the Delete Message is presented.");
        profilePage.isDeletedMessageVisible();

//        int postCountAfterDeleting = profilePage.getPostCount(0);

//

        log.info("STEP 9.: The Post increased after deletion.");
        int postCountAfterDelete = profilePage.checkPostCountAfterDeletion();
        Assert.assertEquals( postCountAfterDelete, initialPostCount - 1  );


    }

    }


//    @Test(priority = 1) ????
//    public void verifyUserCanLikePost() throws InterruptedException {
//        HomePage homePage = new HomePage(super.driver, log);
//        LoginPage loginPage = new LoginPage(super.driver, log);
//
//        log.info("The user has navigated to the Login page.");
//        loginPage.navigateToLoginPage();
//
//        log.info("The user has logged in with username and password.");
//        loginPage.loginWithUSerAndPassword(testUser, testPassword);
//
//        log.info("The user has navigated to the Profile page.");
//        homePage.clickOnNavBarProfile();
//
//        ProfilePage profilePage = new ProfilePage(super.driver, log);
//        profilePage.clickPost(0);
//        log.info("The user has clicked on the first post.");
//
//        profilePage.ClickOnLikeButton();
//        log.info("The user has clicked on the like button.");
//        profilePage.isLikeMessageVisible();
//
//    }
