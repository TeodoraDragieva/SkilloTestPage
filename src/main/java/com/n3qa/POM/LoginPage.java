package com.n3qa.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    //1 CONST
    // USER NAME PASSWORD
   public static final String LOGIN_PAGE = "/users/login";

    //2 STARTING with UI MAP
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


    //3 CONSTRUCTOR
    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    //3.1. NAVIGATION
    public void navigateToLoginPage(){
        navigateTo(LOGIN_PAGE);
    }

    // USER ACTIONS
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

    //Support methods
    public String getLoginPageFormTitle(){
        //Step 3  Use explicit condition to check the visibility
        wait.until(ExpectedConditions.visibilityOf(loginFormHeaderTitle));
        //Step 4 Try to get the value of the text
        String actualTitleText = loginFormHeaderTitle.getText();
        //Step 5 return the getted text in to a var
        return actualTitleText;
    }

    public String getLoginActionMessage(){
        wait.until(ExpectedConditions.visibilityOf(loginFormToastMessage));
        String msg = loginFormToastMessage.getText();
        return msg;
    }

    //Support methods
    // get Placeholders text

    //Support methods
    // Is shown



}
