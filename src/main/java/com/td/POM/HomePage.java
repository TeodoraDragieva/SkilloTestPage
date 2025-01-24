package com.td.POM;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public static final String HOME_PAGE_URL = "/posts/all";

    @FindBy(id = "nav-link-home")
    private WebElement navBarHome;
    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPost;
    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfile;
    @FindBy(id = "nav-link-login")
    private WebElement navBarLogin;
    @FindBy (css = "input#search-bar.form-control[formcontrolname=\"query\"]")
    private WebElement searchBar;
    @FindBy (css = "a.post-user[href=\"/users/9099\"]")
    private WebElement userNameFoundAfterSearch;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        navigateTo(HOME_PAGE_URL);
    }

    public boolean isNavBarHomeLinkShown() {
      return isPresented(navBarHome);
    }

    public void clickOnNavBarHome() { waitAndClickOnWebElement(navBarHome);}

    public boolean isNavLoginShown() {
     return isPresented(navBarLogin);
    }

    public void clickOnNavBarLogin () {
       waitAndClickOnWebElement(navBarLogin);
    }

    public void clickOnNavBarProfile(){
        waitAndClickOnWebElement(navBarProfile);
    }

    public boolean isNavBarProfilePresented(){ return isPresented(navBarProfile);}

    public void clickOnNavBarNewPost () {
       waitAndClickOnWebElement(navBarNewPost);
    }

    public void searchAndSelectMember(String memberName) {
        waitPageTobeFullyLoaded();
        searchBar.sendKeys(memberName); // Търсене по име
        searchBar.sendKeys(Keys.ENTER); // Потвърждение на търсенето

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.post-user[href=\"/users/9099\"]")));
        log.info("Search result is displayed for: " + memberName);
    }

    public void clickOnUserProfileFoundAfterSearching () {
        isPresented(userNameFoundAfterSearch);
        waitAndClickOnWebElement(userNameFoundAfterSearch);}
}

