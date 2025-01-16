package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    private List<WebElement> posts;
    @FindBy(css = "label.btn-private.active > input[type='radio']")
    private WebElement privateButtonInProfilePage;

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void clickOnLikeButton() {
        waitAndClickOnWebElement(likeButton);
    }

    public void clickOnUserWithUploadedPic() {
        waitAndClickOnWebElement(selectUserProfile38);
    }

    public void clickOnYesButton() {
        waitAndClickOnWebElement(areYouSureYesButton);
    }

    public void clickOnDeleteButton() {
        waitAndClickOnWebElement(deletePostButton);
    }

    public int getPostCount() {
        return posts.size();
    }

    public int getLastPostIndex() {
        return getPostCount() - 1;
    }

    public void clickPost(int index) {
        if (index >= 0 && index < getPostCount()) {
            WebElement post = posts.get(index);
            wait.until(ExpectedConditions.visibilityOf(post));
            post.click();
        } else {
            throw new IndexOutOfBoundsException("Invalid post with index: " + index);
        }
    }

    public int countAllPostsWithScroll() {
        By postLocator = By.cssSelector("app-post.app-post");
        int postCount = 0;
        int newPostCount = 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        do {
            postCount = driver.findElements(postLocator).size();
            scrollToBottom();  // Използваме новия метод

            try {
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(postLocator, postCount));
            } catch (TimeoutException e) {
                log.info("Reached the end of the page. Total posts: " + postCount);
                break;
            }

            newPostCount = driver.findElements(postLocator).size();

        } while (newPostCount > postCount);

        return newPostCount;
    }

    public int countAllPostsWithScrollUp() {
        By postLocator = By.cssSelector("app-post.app-post");
        int postCount = driver.findElements(postLocator).size();
        int previousPostCount;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        do {
            previousPostCount = postCount;
            postCount = driver.findElements(postLocator).size();
            scrollToTop();  // Използваме новия метод

            try {
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(postLocator, previousPostCount));
            } catch (TimeoutException e) {
                log.info("Reached the top of the page or no more new posts. Total posts: " + postCount);
                break;
            }
        } while (postCount > previousPostCount);

        return postCount;
    }


    private boolean isElementVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Element is not visible: " + element);
            return false;
        }
    }

    public boolean isDeletedMessageVisible() {
        return isElementVisible(confirmDeletionMessage);
    }

    public boolean isLikeMessageVisible() {
        return isElementVisible(postLikeMessage);
    }

    public boolean isDislikeMessageVisible() {
        return isElementVisible(postDislikeMessage);
    }

    public void closePostModal() {
        waitAndClickOnWebElement(userLinkLocator);
    }

}
