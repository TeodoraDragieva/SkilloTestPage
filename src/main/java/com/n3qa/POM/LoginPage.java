package com.n3qa.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);

    }

    //UI ELEMENTS
    //C REATE LOCATORS FOR THE WEB ELEMENTS

    WebElement loginFormUserNameInputField = driver.findElement(By.id("defaultLoginFormUsername"));

   public void provideCredentilas() {
       waitAndTypeTextInField(loginFormUserNameInputField, "IvanVazov");
   }

}
