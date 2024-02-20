package com.bit.utilities;

import org.openqa.selenium.*;

public class ReusableFunctions {

    WebDriver driver;

    public ReusableFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}//class
