package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
   public static final String LOGIN_PAGE = "/users/login";
    public static final String LOGIN_FORM_TITLE = "Sign in";

    @FindBy(css = "p.h4")
    private WebElement loginFormHeaderTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement loginFormUserNameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement loginFormPasswordInputField;
    @FindBy (xpath = "//span[contains(text(),'Remember me')]")
    private WebElement loginFormRememberMeInputField;
    @FindBy (xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement loginFormRememberMeCheckBoxLabelText;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy (xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegisterPageLink;
    @FindBy (css = ".toast-message.ng-star-inserted")
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
        waitAndClickOnWebElement(loginFormSubmitButton);
    }

    public boolean loginButtonIsShown(){
        isPresented(loginFormSubmitButton);
        return true;
    }
    
    public void loginWithUSerAndPassword(String user, String password){
        provideUserName(user);
        providePassword(password);
        clickOnLoginButton();
    }

    public String getLoginPageFormTitle(){
        wait.until(ExpectedConditions.visibilityOf(loginFormHeaderTitle));
        return loginFormHeaderTitle.getText();
    }

    public String getLoginPageUrl(){
        WebElement requestedUrl = null;
        wait.until(ExpectedConditions.visibilityOf(requestedUrl));
        return loginFormHeaderTitle.getText();
    }

    public String getLoginActionMessage(){
        wait.until(ExpectedConditions.visibilityOf(loginFormToastMessage));
        String msg = loginFormToastMessage.getText();
        return msg;
    }

    public boolean isLogoutButtonShown() {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            return true;
        }

    public boolean isPageTitleCorrect() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "ISkillo";
        log.info("Verifying page title. Expected: " + expectedTitle + ", Actual: " + actualTitle);
        return actualTitle.equals(expectedTitle);
    }
}

