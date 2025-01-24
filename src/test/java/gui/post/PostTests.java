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

public class PostTests extends BaseTest {

    private static final String HOME_PAGE_URL = "/posts/all";
    File postPicture = new File("src/test/resources/upload/pirin.jpg");

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
        boolean isShownNavBarHome = homePage.isNavBarHomeLinkShown();
        Assert.assertTrue(isShownNavBarHome, "Navigation Bar Home link is NOT presented.");

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
        loginPage.loginWithUserAndPassword("tedi90@abv.bg", "Pukanka2");

        log.info ("STEP 4.: Verify the navigation bar Home link is presented.");
        homePage.isNavBarHomeLinkShown();

        log.info ("STEP 5.: Verify the navigation bare Profile link is presented and click on it.");
        homePage.clickOnNavBarProfile();

        log.info("STEP 6.: Verify the User is navigated to the Profile Page");

        log.info("STEP 6.1.: Verify if the URL is for the Profile Page");
        ProfilePage profilePage = new ProfilePage(super.driver,log);
        boolean isProfilePageLoaded = profilePage.isUrlLoaded(PROFILE_PAGE);
        Assert.assertTrue(isProfilePageLoaded);

        log.info("STEP 7.: Verify there is a post - to be deleted.");
        int initialPostCount = profilePage.countAllPostsWithScroll();
        Assert.assertTrue(initialPostCount > 0);

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
        Assert.assertEquals(postUserTxt, "tedi90@abv.bg");

        log.info ("STEP 12.5.: Verify there is one more post shown.");
        int postFinalCount = profilePage.countAllPostsWithScroll ();
        Assert.assertTrue(postFinalCount == initialPostCount + 1, "Posts didn't increase by 1.");

    }

    @Test (priority = 1)
    public void verifyUserCanLikeOtherMembersPost() throws InterruptedException {

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
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the Login page via click on navigation bar Login link.");
        homePage.clickOnNavBarLogin();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1.: Verify the User successfully landed on the Login Page.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3.: Provide username and password and clicking on Login Button.");
        loginPage.loginWithUserAndPassword("testingDemos", "testing");

        log.info("STEP 5.: Verify the User is navigated to the Home Page");

        log.info ("STEP 5.1.: Verify the navigation bare Home link is presented.");
        homePage.isNavBarHomeLinkShown();

        log.info("STEP 5.2.: Verify if the URL is for the Home Page");
        boolean isHomePageLoaded = homePage.isUrlLoaded(HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded);

        log.info("STEP 6.: The User search and select the User Profile - tedi90@abv.bg");

        homePage.searchAndSelectMember("tedi90@abv.bg");
        homePage.clickOnUserProfileFoundAfterSearching();

        ProfilePage profilePage = new ProfilePage(super.driver,log);

        log.info("STEP 7.: The user click on the first post of the Other User.");
        profilePage.clickPost(0);

        log.info("STEP 8. :The user has clicked on the like button.");
        profilePage.clickOnLikeButton();

        log.info("STEP 9. :Verify if the Like Message is visible.");
        profilePage.isLikeMessageVisible();

        log.info("STEP 10. :Close the post Modal.");
        profilePage.closePostModal();
    }

    @Test (priority = 2)
    public void verifyUserCanDislikeOtherMembersPost() {
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
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the Login page via click on navigation bar Login link.");
        homePage.clickOnNavBarLogin();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 2.1.: Verify the User successfully landed on the Login Page.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3.: Provide username and password and clicking on Login Button.");
        loginPage.loginWithUserAndPassword("testingDemos", "testing");

        log.info("STEP 5.: Verify the User is navigated to the Home Page");

        log.info ("STEP 5.1.: Verify the navigation bare Home link is presented.");
        homePage.isNavBarHomeLinkShown();

        log.info("STEP 5.2.: Verify if the URL is for the Home Page");
        boolean isHomePageLoaded = homePage.isUrlLoaded(HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded);

        log.info("STEP 6.: The User search and select the User Profile - tedi90@abv.bg");

        homePage.searchAndSelectMember("tedi90@abv.bg");
        homePage.clickOnUserProfileFoundAfterSearching();

        ProfilePage profilePage = new ProfilePage(super.driver,log);

        log.info("STEP 7.: The user has clicked on the first post of the Other User.");
        profilePage.clickPost(0);

        log.info("STEP 8. :The user has clicked on the like button.");
        profilePage.clickOnLikeButton();

        log.info("STEP 9. :Verify if the Like Message is visible.");
        profilePage.isDislikeMessageVisible();

        log.info("STEP 10. :Close the post Modal.");
        profilePage.closePostModal();
    }

    @Test(priority = 3)
    public void verifyUserCanDeletePost() {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1: Navigating to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Verifying user is on the Login Page.");
        Assert.assertEquals(loginPage.getLoginPageFormTitle(), LOGIN_FORM_TITLE, "Incorrect login page title.");

        log.info("STEP 3: Logging in with valid credentials.");
        loginPage.loginWithUserAndPassword("tedi90@abv.bg", "Pukanka2");

        log.info("STEP 4: Clicking on Profile link in navigation bar.");
        homePage.clickOnNavBarProfile();

        log.info("STEP 5: Verifying the user is navigated to the Profile Page.");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        Assert.assertTrue(profilePage.isUrlLoaded(PROFILE_PAGE), "Failed to navigate to Profile Page.");

        log.info("STEP 6: Checking for existing posts to delete.");
        int initialPostCount = profilePage.countAllPostsWithScroll();
        Assert.assertTrue(initialPostCount > 0, "No posts available to delete.");

        log.info("STEP 7: Selecting and deleting the first post.");
        profilePage.clickPost(0);
        profilePage.clickOnDeleteButton();
        profilePage.clickOnYesButton();

        log.info("STEP 8: Verifying deletion confirmation message.");
        Assert.assertTrue(profilePage.isDeletedMessageVisible(), "Deletion confirmation message not displayed.");

        log.info("STEP 9: Verifying post count decreased.");
        int postCountAfterDelete = profilePage.countAllPostsWithScrollUp();
        Assert.assertEquals(postCountAfterDelete, initialPostCount - 1, "Post count did not decrease by 1.");
    }
}

