package com.bit.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;

public class ReusableFunctions {

    WebDriver driver;
    static int maxTimeout = 30;

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

    /*Wait for the element to be clickable ignoring the StaleElementReferenceException*/
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

    /*Wait for the Alert present ignoring the StaleElementReferenceException*/
        public static boolean waitForAlertPresent(WebDriver driver, int waitTime) {
        boolean flag = false;
        new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert();
            return flag = true;
        } catch (Exception Ex) {
            return flag;
        }
    }

    /**
     * This method is used to wait for element till visibility of element.
     *
     * @param driver
     * @param attributeValue
     *            - provide locator value of element till it is visible on
     *            application and then click that element.
     * @param waitTime
     *            - provide maximum wait time in seconds for driver
     */
    public static boolean waitForElementToBeVisible(WebDriver driver, By attributeValue, int waitTime) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(attributeValue));
            flag = true;
            return flag;
        } catch (Exception Ex) {
            return flag;
        }
    }

    /**
     * This method is to Switch to an iframe
     *
     * @param driver
     * @param attributeValue-This
     *            is the unique attribute of the frame to be switched
     */
    public static void switchToFrame(WebDriver driver, By attributeValue) {
        try {
            if (ReusableFunctions.waitForElementToBePresentBool(driver, attributeValue, maxTimeout)) {
                String iframe = driver.findElement(attributeValue).toString();
                driver.switchTo().frame(iframe);
                System.out.println("Switched to frame");
            } else {
                System.out.println("Frame not found");
            }
        } catch (Exception Ex) {
            System.out.println("Exception occured");
        }
    }

    /**
     * This method is to Switch to an default iframe
     *
     * @param driver
     * @param //attributeValue-This
     *            is the unique attribute of the frame to be switched
     */
    public void switchToDefaultFrame(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();

        } catch (Exception Ex) {
            System.out.println("Exception occured");
        }
    }

    /*Move to Element and Click Action in Selenium*/
    public static void MouseClickActionMoveToElement(WebDriver driver, By attributeValue) {
        try {
            if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
                WebElement element = driver.findElement(attributeValue);
                //element.click();
                Actions builder = new Actions(driver);
                builder.moveToElement(element).click().build().perform();
                System.out.println("Able to locate and click to element !");
            } else {
                System.out.println("Not able to locate the element !");
            }
        } catch (Exception Ex) {
            System.out.println("Exception occured");
        }
    }

    /*Get text from the element and return as string*/
    public String getTextFromElement(WebDriver driver, By locator) {
        String text = null;
        try {
            if (waitForElementToBePresentBool(driver, locator, maxTimeout)) {
                WebElement element = driver.findElement(locator);
                text = element.getText();
                System.out.println("Element Text is: " + text);

            } else {
                System.out.println("Element not present !");

            }
        } catch (Exception Ex) {
            System.out.println("Exception occured");

        }
        return text;
    }

    private static boolean waitForElementToBePresentBool(WebDriver driver, By locator, int maxTimeout) {
        return false;
    }

    /**
     * @Method:getcurrenttime This method is used to return system time in
     *                        seconds.
     */
    public static int getcurrenttime() {
        long currentDateMS = new Date().getTime();
        int seconds = (int) ((currentDateMS / 1000) % 3600);
        return seconds;
    }

    /**
     * @Method:closeAllOtherWindows - This method is used to close all open
     *                              windows except parent window.
     * @param driver
     * @return
     * @throws InterruptedException
     */
    public static boolean closeAllOtherWindows(WebDriver driver) throws InterruptedException {
        String Parent_Window = driver.getWindowHandle();
        java.util.Set<String> allWindowHandles = driver.getWindowHandles();
        for (String currentWindowHandle : allWindowHandles) {
            if (!currentWindowHandle.equals(Parent_Window)) {
                driver.switchTo().window(currentWindowHandle);
                driver.close();
                Thread.sleep(2000);
            }
        }
        driver.switchTo().window(Parent_Window);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }
    /**
     * This method is for simple dropdown selection by visibleText
     *
     * @param driver
     * @param dropDownID-This
     *            is the unique attribute to find an dropdownelement
     * @param dropDownValue-This
     *            is the text to search in dropdown
     */
    public static void dropDownSelectionByText(WebDriver driver, By dropDownID, String dropDownValue) {
        try {
            WebElement element = null;
            new WebDriverWait(driver,Duration.ofSeconds(maxTimeout) ).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(dropDownID));
            element = driver.findElement(dropDownID);
            Select dropDown = new Select(element);
            dropDown.selectByVisibleText(dropDownValue);
        }
        catch (StaleElementReferenceException ex) {
            System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
        }
    }

    /**
     * This method is for simple dropdown selection by value
     *
     * @param driver
     * @param dropDownID-This
     *            is the unique attribute to find an dropdownelement
     * @param dropDownValue-This
     *            is the text to search in dropdown
     */
    public static void dropDownSelectionByValue(WebDriver driver, By dropDownID, String dropDownValue) {
        try {
            WebElement element = null;
            new WebDriverWait(driver, Duration.ofSeconds(maxTimeout)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(dropDownID));
            element = driver.findElement(dropDownID);
            Select dropDown = new Select(element);
            dropDown.selectByValue(dropDownValue);
        }
        catch (StaleElementReferenceException ex) {
            System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
        }
    }

    /**
     * This method is for simple dropdown selection by index
     *
     * @param driver
     * @param dropDownID-This
     *            is the unique attribute to find an dropdownelement
     * @param dropDownValue-This
     *            is the text to search in dropdown
     */
    public static void dropDownSelectionByIndex(WebDriver driver, By dropDownID, int dropDownValue) {
        try {
            WebElement element = null;
            new WebDriverWait(driver, Duration.ofSeconds(maxTimeout)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(dropDownID));
            element = driver.findElement(dropDownID);
            Select dropDown = new Select(element);
            dropDown.selectByIndex(dropDownValue);
        }
        catch (StaleElementReferenceException ex) {
            System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
        }
    }



}//class
