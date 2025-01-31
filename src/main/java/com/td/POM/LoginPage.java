package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class LoginPage extends BasePage {
   private static final String LOGIN_PAGE = "/users/login";
    private static final String LOGIN_FORM_TITLE = "Sign in";

    @FindBy(css = "p.h4")
    private WebElement loginFormHeaderTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement loginFormUserNameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement loginFormPasswordInputField;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy (xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegisterPageLink;
    @FindBy (css = "//div[contains(text(), 'Successful login')]")
    private WebElement loginFormToastMessage;
    @FindBy (css = "i.fas.fa-sign-out-alt.fa-lg\n")
    private WebElement logoutButton;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToLoginPage(){
        navigateTo(LOGIN_PAGE);
    }

    public void provideUserName(String userName) {
        isPresented(loginFormUserNameInputField);
        waitAndTypeTextInField(loginFormUserNameInputField,userName);
    }

    public void providePassword(String password) {
        isPresented(loginFormUserNameInputField);
        waitAndTypeTextInField(loginFormPasswordInputField,password);
    }

    public void clickOnLoginButton(){
        clickOn(loginFormSubmitButton);
    }

    public void loginWithIskilloUser() {

    }

    public boolean loginButtonIsShown(){
        return isPresented(loginFormSubmitButton);
    }
    
    public void loginWithUserAndPassword(String user, String password){
        provideUserName(user);
        providePassword(password);
        clickOn(loginFormSubmitButton);
    }

    public String getLoginPageFormTitle(){
        wait.until(ExpectedConditions.visibilityOf(loginFormHeaderTitle));
        return loginFormHeaderTitle.getText();
    }

    public String getLoginActionMessage(){
        wait.until(ExpectedConditions.visibilityOf(loginFormToastMessage));
        return loginFormToastMessage.getText();
    }

    public boolean isLogoutButtonShown() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            log.info("Logout button is visible.");
            return true;
        } catch (NoSuchElementException e) {
            log.error("ERROR: Logout button is not visible.", e);
            return false;
        }
    }

   public void clickOnLogOutButton () {
        clickOn(logoutButton);
   }

    public boolean isPageTitleCorrect() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "ISkillo";
        log.info("Verifying page title. Expected: " + expectedTitle + ", Actual: " + actualTitle);
        return actualTitle.equals(expectedTitle);
    }
}

