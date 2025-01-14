package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage  extends BasePage {

    public static final String REGISTRATION_PAGE_URL = "/users/register";
    public static final String REGISTRATION_FORM_TITLE = "Sign up";

    @FindBy(xpath = "//h4[contains(text(),'Sign up')]")
    private WebElement regFormHeaderTitle;
    @FindBy (xpath = "//input[@formcontrolname='username']")
    private WebElement regFormUserNameInputField;
    @FindBy (css = "input[placeholder='email']")
    private WebElement emailPlaceholder;
    @FindBy (xpath = "//input[@formcontrolname='birthDate']")
    private WebElement dateOfBirthPlaceholder;
    @FindBy (xpath = "//input[@id='defaultRegisterFormPassword']")
    private WebElement regPasswordPlaceholder;
    @FindBy (xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmRegPasswordPlaceholder;
    @FindBy (xpath = "//textarea[@placeholder='Public info']")
    private WebElement publicInfoPlaceHolder;
    @FindBy (id = "sign-in-button")
    private WebElement signInButton;
    @FindBy (css = "div.toast-message.ng-star-inserted")
    private WebElement registrationFormToastMsg;

    public RegistrationPage (WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToRegistrationPage (){
        navigateTo(REGISTRATION_PAGE_URL);
    }


    public void provideUserName(String userName) {
        isPresented(regFormUserNameInputField);
        waitAndTypeTextInField(regFormUserNameInputField,userName);
    }

    public void provideEmail(String email) {
        isPresented(emailPlaceholder);
        waitAndTypeTextInField(emailPlaceholder,email);}

    public void provideDateOfBirth(String dateofbirth) {
        isPresented(dateOfBirthPlaceholder);
        waitAndTypeTextInField(dateOfBirthPlaceholder,dateofbirth);}

    public void providePasswordForRegistrationForm(String password) {
        isPresented(regPasswordPlaceholder);
        waitAndTypeTextInField(regPasswordPlaceholder,password);}

    public void provideSamePasswordForRegistrationForm(String password) {
        isPresented(confirmRegPasswordPlaceholder);
        waitAndTypeTextInField(confirmRegPasswordPlaceholder,password);}

    public void providePublicInfo(String publicinfo) {
        isPresented(publicInfoPlaceHolder);
        waitAndTypeTextInField(publicInfoPlaceHolder,publicinfo);}

    public void clickOnSignInButton(){
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        waitAndClickOnWebElement(signInButton);
    }


    public String getRegPageFormTitle(){
        wait.until(ExpectedConditions.visibilityOf(regFormHeaderTitle));
        return regFormHeaderTitle.getText();
    }

    public String getRegistrationActionMessage(){
        wait.until(ExpectedConditions.visibilityOf(registrationFormToastMsg));
        String msg = registrationFormToastMsg.getText();
        return msg;
    }

    public boolean signInButtonIsShown(){
        isPresented(signInButton);
        return true;
    }

}
