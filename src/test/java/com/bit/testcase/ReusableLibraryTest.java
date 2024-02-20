package com.bit.testcase;

import com.bit.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.bit.utilities.ReusableFunctions.*;
import static com.bit.utilities.ReusableFunctions.isElementPresent;

public class ReusableLibraryTest extends BaseTest {

    @Test
    public void isElementPresentTest()
    {
        String url = config.getProperty("url");
        int waitTime = Integer.parseInt(config.getProperty("waitTime"));
        driver.get(url);
        String googleSearchTxt_ID = or.getProperty("googleSearchTxt_ID");
        Assert.assertTrue(isElementPresent(driver, By.id(googleSearchTxt_ID),waitTime));

    }

    @Test
    public void waitForElementToBeClickableBoolTest()
    {
        String url = config.getProperty("url");
        int waitTime = Integer.parseInt(config.getProperty("waitTime"));
        driver.get(url);
        String googleSearchTxt_ID = or.getProperty("googleSearchTxt_ID");
        driver.findElement(By.id(googleSearchTxt_ID)).sendKeys("Selenium", Keys.ENTER);
        WebElement elm=driver.findElement(By.xpath("//a[@href='https://www.selenium.dev/']//h3[@class='LC20lb MBeuO DKV0Md'][normalize-space()='Selenium']"));
        if(waitForElementToBeClickableBool(driver,By.xpath("//a[@href='https://www.selenium.dev/']//h3[@class='LC20lb MBeuO DKV0Md'][normalize-space()='Selenium']"),waitTime)) {
            elm.click();
        }
        else
            System.out.println("Element not found");
        Assert.assertTrue(isElementPresent(driver, By.xpath("//span[@class='navbar-logo']//*[name()='svg']"),waitTime));

    }

    @Test
    public void waitForAlertPresentTest()
    {

        String url = "https://the-internet.herokuapp.com/javascript_alerts";
        int waitTime = Integer.parseInt(config.getProperty("waitTime"));
        driver.get(url);
        WebElement elm=driver.findElement(By.xpath("/html/body/div[2]/div/div/ul/li[1]/button"));
        elm.click();
        if(waitForAlertPresent(driver,waitTime)) {
            driver.switchTo().alert().accept();
        }
        else
            System.out.println("Element not found");
        Assert.assertTrue(isElementPresent(driver, By.xpath("//*[@id=\"result\"]"),waitTime));

    }

    @Test
    public void waitForElementToBeVisibleTest()
    {

        String url = "https://the-internet.herokuapp.com/add_remove_elements/";
        int waitTime = Integer.parseInt(config.getProperty("waitTime"));
        driver.get(url);
        WebElement elm=driver.findElement(By.xpath("/html/body/div[2]/div/div/button"));
        if(isElementPresent(driver,By.xpath("/html/body/div[2]/div/div/button"),waitTime)) {
            elm.click();
            System.out.println("Clicked on Add Element button");
        }
        else
            System.out.println("Element not found");
        if(waitForElementToBeVisible(driver,By.xpath("/html/body/div[2]/div/div/div/button"),waitTime))
        {
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/button")).click();
            System.out.println("Clicked on Delete button");
        }
        else
            System.out.println("Element not found");
        Assert.assertFalse(isElementPresent(driver, By.xpath("/html/body/div[2]/div/div/div/button"),waitTime));

    }



}
