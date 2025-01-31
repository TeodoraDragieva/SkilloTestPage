package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProfilePage extends BasePage {

    public static final String PROFILE_PAGE = "/users/9099";

    @FindBy(xpath = "//i[contains(@class,'like far fa-heart fa-2x')]")
    private WebElement likeButton;
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
    private WebElement userProfileLink;
    @FindBy(css = "app-post.app-post")
    private List<WebElement> posts;

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void clickOnLikeButton() {
        clickOn(likeButton);
    }

    public void clickOnUserWithUploadedPic() {
        clickOn(selectUserProfile38);
    }

    public void clickOnYesButton() {
        clickOn(areYouSureYesButton);
    }

    public void clickOnDeleteButton() {
        clickOn(deletePostButton);
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
        clickOn(userProfileLink);
    }

}
