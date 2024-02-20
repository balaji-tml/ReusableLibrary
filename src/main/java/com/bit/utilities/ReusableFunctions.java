package com.bit.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableFunctions {

    WebDriver driver;

    public ReusableFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public static boolean isElementPresent(WebDriver driver, By by, int waitTime) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitForElementToBeClickableBool(WebDriver driver, By attributeValue, int waitTime) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(attributeValue));
            flag = true;
            return flag;

        } catch (Exception Ex) {
            return flag;
        }
    }

    public static boolean waitForAlertPresent(WebDriver driver, int waitTime) {
        boolean flag = false;
        new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.alertIsPresent());
        try{
            driver.switchTo().alert();
            return flag = true;
        }catch(Exception Ex){
            return flag;
        }
    }


}//class
