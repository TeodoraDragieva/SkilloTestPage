package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProfilePage extends BasePage {

    public static final String PROFILE_PAGE = "/users/5646";

    @FindBy(xpath = "//div[contains(@class,'edit-profile-pic')]")
    private WebElement uploadImage;
    @FindBy(id = "upload-img")
    private WebElement hiddenUploadImage;
    @FindBy(xpath = "//i[contains(@class,'like far fa-heart fa-2x')]")
    private WebElement likeButton;
    @FindBy(xpath = "//i[contains(@class,'ml-4 far fa-thumbs-down fa-2x')]")
    private WebElement dislikeButton;
    @FindBy(xpath = "//label[contains(@class,'delete-ask')]")
    private WebElement deletePostButton;
    @FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-sm')]")
    private WebElement areYouSureYesButton;
    @FindBy(xpath = "//div[contains(@aria-label,'Post Deleted!')]")
    private WebElement confirmDeletionMessage;
    @FindBy(xpath = "//div[contains(@aria-label,'Post liked')]")
    private WebElement postLikeMessage;
    @FindBy(xpath = "//div[contains(@aria-label,'Post disliked')]")
    private WebElement postDislikeMessage;
    @FindBy(xpath = "//a[@href='/users/38']")
    private WebElement selectUserProfile38;
    @FindBy(xpath = "//a[@class='post-user']")
    private WebElement userLinkLocator;
    @FindBy(css = "app-post.app-post")
    private WebElement postsLocator;
    @FindBy (css = "label.btn-private.active > input[type='radio']")
    private  WebElement privateButtonInProfilePage;

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void ClickOnYesButton() {
        waitAndClickOnWebElement(areYouSureYesButton);
    }

    public void ClickOnLikeButton() {
        waitAndClickOnWebElement(likeButton);
    }

    public void ClickOnUserWithUploadedPic() {
        waitAndClickOnWebElement(selectUserProfile38);
    }

    Actions action = new Actions(driver);

    public void HoverOverProfilePicture() {
        action.moveToElement(uploadImage).perform();
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public void ClickOnDeleteButton() {
        waitAndClickOnWebElement(deletePostButton);
    }

    public void clickOnPrivateButton() {
        waitAndClickOnWebElement(privateButtonInProfilePage);
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.cssSelector("app-post.app-post"));
            wait.until(ExpectedConditions.visibilityOf(postsLocator));
            return posts.size();
    }

    public void clickPost(int index) {
            List<WebElement> posts = driver.findElements(By.cssSelector("app-post.app-post"));
        if (index >= 0 && index < posts.size()) {
            WebElement post = posts.get(index);
                wait.until(ExpectedConditions.visibilityOf(post));
                post.click();
            } else {
                throw new IndexOutOfBoundsException("Invalid post with index: " + index);
            }
}
//        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
//        posts.get(postIndex).click();
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("return document.readyState").equals("complete");


//    public void verifyThereAreMorePostShown() {
//
//        ProfilePage profilePage = new ProfilePage(super.driver, log);
//        boolean isMorePostShown = profilePage.getPostCount() > 0;
//        Assert.assertTrue(isMorePostShown);
//    }

    public boolean isDeletedMessageVisible() {
        boolean isDeletedMessageVisible = false;
        try {
            isDeletedMessageVisible = wait.until(ExpectedConditions.visibilityOf(confirmDeletionMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post is Deleted! Message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post Deleted! message is not displayed!");
            isDeletedMessageVisible = false;
        }
        return isDeletedMessageVisible;
    }

    public boolean isLikeMessageVisible() {
        boolean isLikeMessageVisible = false;
        try {
            isLikeMessageVisible = wait.until(ExpectedConditions.visibilityOf(postLikeMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post liked message is displayed.");
            isLikeMessageVisible = true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post liked message is not displayed!");
            isLikeMessageVisible = false;
        }
        return isLikeMessageVisible;
    }

    public boolean isDislikeMessageVisible() {
        boolean isDislikeMessageVisible = false;
        try {
            isDislikeMessageVisible = wait.until(ExpectedConditions.visibilityOf(postDislikeMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post disliked message is displayed.");
            isDislikeMessageVisible = true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post disliked message is not displayed!");
            isDislikeMessageVisible = false;
        }
        return isDislikeMessageVisible;
    }

    public void closePostModal() {
        waitAndClickOnWebElement(userLinkLocator);
    }

    public void deleteWithConfirmationPost() {
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.ClickOnDeleteButton();
        profilePage.ClickOnYesButton();
    }

    public int checkPostCountAfterDeletion() {
        List<WebElement> posts = driver.findElements(By.cssSelector("app-post.app-post"));
        return posts.size();
    }

}


