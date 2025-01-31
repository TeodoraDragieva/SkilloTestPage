package com.td.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    private static final String BASE_URL = "http://training.skillo-bg.com:4300";
    static WebDriver driver;
    static WebDriverWait wait;
    Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickOn(WebElement elm) {
        wait.until(ExpectedConditions.visibilityOf(elm));
        wait.until(ExpectedConditions.elementToBeClickable(elm));
        elm.click();
        waitPageTobeFullyLoaded();
    }

    public void waitAndTypeTextInField(WebElement textField, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(inputText);

        for (int i = 0; i < 3; i++) {
            if (inputText.equals(textField.getText())) {
                log.info("Text field successfully populated with: " + inputText);
                return;
            }
            log.warn("Text field did not populate correctly. Retrying.");
            textField.clear();
            textField.sendKeys(inputText);
        }

        if (inputText.equals(textField.getText())) {
            throw new IllegalStateException("Failed to populate text field with: " + inputText);
        }
    }

    public void navigateTo(String pageURLSuffix) {
        String currentURL = BASE_URL + pageURLSuffix;

        driver.get(currentURL.toLowerCase());
        log.info("CONFIRM # The user has navigated to: " +currentURL);

        waitPageTobeFullyLoaded();
    }

    public boolean isUrlLoaded(String pageURL) {
        waitPageTobeFullyLoaded();
        return wait.until(ExpectedConditions.urlContains(pageURL));
    }

    public static void waitPageTobeFullyLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public boolean isPresented(WebElement elm) {
        boolean isWebElmShown = false;
        String li = locatorInfo(elm);

        log.info("ACTION @ The user is verifying if the web element with locator info: " + li);
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            log.info("# SHOWN  # Web element is shown with locator info" + li);
            isWebElmShown = true;
        } catch (TimeoutException e) {
            log.error("# NOT SHOWN! # Web element is NOT shown with locator info" + li);
            isWebElmShown = false;
        }
        return isWebElmShown;
    }

    private String locatorInfo(WebElement elm){
        String[] rawWebElmInfo = elm.toString().split("->");
        String[] webElmInfo = rawWebElmInfo[1].split(":");
        String locatorStrategy = webElmInfo[0];
        String locatorExpression = webElmInfo[1];
        String info  = "LOCATOR STRATEGY BY: "+locatorStrategy.toUpperCase()+" LOCATOR EXPRESSION: "+locatorExpression;
        return  info;
    }

    public void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public boolean isPageTitleCorrect() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "ISkillo";
        log.info("Verifying page title. Expected: " + expectedTitle + ", Actual: " + actualTitle);
        return actualTitle.equals(expectedTitle);
    }

    public int countAllPostsWithScroll() {
        By postLocator = By.cssSelector("app-post.app-post");
        int totalPostCount = 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        while (true) {
            int currentPostCount = driver.findElements(postLocator).size();
            log.info("Current post count: " + currentPostCount);

            scrollToBottom();

            try {
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(postLocator, currentPostCount));
            } catch (TimeoutException e) {
                log.info("Reached the end of the page. Total posts: " + currentPostCount);
                totalPostCount = currentPostCount;
                break;
            }
        }

        log.info("Final post count returned: " + totalPostCount);
        return totalPostCount;
    }


    public int countAllPostsWithScrollUp() {
        By postLocator = By.cssSelector("app-post.app-post");
        int postCount = driver.findElements(postLocator).size();
        int previousPostCount;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        do {
            previousPostCount = postCount;
            postCount = driver.findElements(postLocator).size();
            scrollToTop();

            try {
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(postLocator, previousPostCount));
            } catch (TimeoutException e) {
                log.info("Reached the top of the page or no more new posts. Total posts: " + postCount);
                break;
            }
        } while (postCount > previousPostCount);

        return postCount;
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

}