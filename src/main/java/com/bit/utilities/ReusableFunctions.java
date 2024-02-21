package com.bit.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReusableFunctions {

    WebDriver driver;
    static int maxTimeout = 30;

    public ReusableFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public static String acceptPopupMessageBox(final WebDriver driver) {
        String message;
        try {
            Alert alert = driver.switchTo().alert();
            message = alert.getText();
            alert.accept();
        } catch (Exception e) {
            message = null;
        }
        System.out.println("message" + message);
        return message;
    }

    public static String cancelPopupMessageBox(final WebDriver driver) {
        String message;
        try {
            Alert alert = driver.switchTo().alert();
            message = alert.getText();
            alert.dismiss();
        } catch (Exception e) {
            message = null;
        }
        return message;
    }

    //    Close all windows except parent

    /**
     * @param driver
     * @return
     * @throws InterruptedException
     * @Method:closeAllOtherWindows - This method is used to close all open
     * windows except parent window.
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



    //    Select a value in dropdown by Text

    /**
     * This method is for simple dropdown selection by visibleText
     *
     * @param driver
     * @param dropDownID-This    is the unique attribute to find an dropdownelement
     * @param dropDownValue-This is the text to search in dropdown
     */
    public static void dropDownSelectionByText(WebDriver driver, By dropDownID, String dropDownValue) {
        try {
            WebElement element = null;
            new WebDriverWait(driver, Duration.ofSeconds(maxTimeout)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(dropDownID));
            element = driver.findElement(dropDownID);
            Select dropDown = new Select(element);
            dropDown.selectByVisibleText(dropDownValue);
        } catch (StaleElementReferenceException ex) {
            System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
        }
    }

//    Select a value in dropdown by Value

    /**
     * This method is for simple dropdown selection by value
     *
     * @param driver
     * @param dropDownID-This    is the unique attribute to find an dropdownelement
     * @param dropDownValue-This is the text to search in dropdown
     */
    public static void dropDownSelectionByValue(WebDriver driver, By dropDownID, String dropDownValue) {
        try {
            WebElement element = null;
            new WebDriverWait(driver, Duration.ofSeconds(maxTimeout)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(dropDownID));
            element = driver.findElement(dropDownID);
            Select dropDown = new Select(element);
            dropDown.selectByValue(dropDownValue);
        } catch (StaleElementReferenceException ex) {
            System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
        }
    }


//    Select a value in dropdown by Index

    /**
     * This method is for simple dropdown selection by index
     *
     * @param driver
     * @param dropDownID-This    is the unique attribute to find an dropdownelement
     * @param dropDownValue-This is the text to search in dropdown
     */
    public static void dropDownSelectionByIndex(WebDriver driver, By dropDownID, int dropDownValue) {
        try {
            WebElement element = null;
            new WebDriverWait(driver, Duration.ofSeconds(maxTimeout)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(dropDownID));
            element = driver.findElement(dropDownID);
            Select dropDown = new Select(element);
            dropDown.selectByIndex(dropDownValue);
        } catch (StaleElementReferenceException ex) {
            System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
        }
    }

    //    Get text from the element and return as string

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


//    Get current system time

    /**
     * @Method:getcurrenttime This method is used to return system time in
     * seconds.
     */
    public static int getcurrenttime() {
        long currentDateMS = new Date().getTime();
        int seconds = (int) ((currentDateMS / 1000) % 3600);
        return seconds;
    }


    public static boolean isElementPresent(WebDriver driver, By by,int waitTime) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void insertText(WebDriver driver, By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);
    }

    //    Move to Element and Click Action in Selenium

    public void MouseClickActionMoveToElement(WebDriver driver, By attributeValue) {
        try {
            if
//The below method is defined above
            (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
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

    public static void selectRadioButton(WebDriver driver, By locator, String value) {
        List<WebElement> select = driver.findElements(locator);
        for (WebElement element : select) {
            if (element.getAttribute("value").equalsIgnoreCase(value)) {
                element.click();
            }
        }
    }

    public static void selectCheckboxes(WebDriver driver, By locator, String value) {
        List<WebElement> abc = driver.findElements(locator);
        List<String> list = new ArrayList<>(Arrays.asList(value.split(",")));
        for (String check : list) {
            for (WebElement chk : abc) {
                if (chk.getAttribute("value").equalsIgnoreCase(check)) {
                    chk.click();
                }
            }
        }
    }

    //    Switch to another Frame

    /**
     * This method is to Switch to an iframe
     *
     * @param driver
     * @param attributeValue-This is the unique attribute of the frame to be switched
     */
    public void switchToFrame(WebDriver driver, By attributeValue) {
        try {
            int maxTimeout =20;
            if (waitForElementToBePresentBool(driver, attributeValue, maxTimeout)) {
                String iframe = driver.findElement(attributeValue).getAttribute("name");
                driver.switchTo().frame(iframe);
                System.out.println("Switched to frame");
            } else {
                System.out.println("Frame not found");
            }
        } catch (Exception Ex) {
            System.out.println("Exception occured");
        }
    }

    private boolean waitForElementToBePresentBool(WebDriver driver, By attributeValue, int maxTimeout) {
        return false;
    }

    //    Switch to Default Frame

    /**
     * This method is to Switch to an default iframe
     *
     * @param driver
     * @param //attributeValue-This is the unique attribute of the frame to be switched
     */
    public void switchToDefaultFrame(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();

        } catch (Exception Ex) {
            System.out.println("Exception occured");
        }
    }

    public static String tooltipText(WebDriver driver, By locator) {
        return driver.findElement(locator).getAttribute("title");
    }

    //Wait for the element to be clickable ignoring the StaleElementReferenceException
    public boolean waitForElementToBeClickableBool(WebDriver driver, By attributeValue, int waitTime) {
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

//    Wait for the Alert present ignoring the StaleElementReferenceException

    public boolean waitForAlertPresent(WebDriver driver, int waitTime) {
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

//    wait for the element to be visible ignoring the StaleElementReferenceException

    /**
     * This method is used to wait for element till visibility of element.
     *
     * @param driver
     * @param attributeValue - provide locator value of element till it is visible on
     *                       application and then click that element.
     * @param waitTime       - provide maximum wait time in seconds for driver
     */
    public boolean waitForElementToBeVisible(WebDriver driver, By attributeValue, int waitTime) {
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







}//class
