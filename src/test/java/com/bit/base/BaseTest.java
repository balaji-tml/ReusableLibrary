package com.bit.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver = null;

    FileInputStream fis = null;

    protected Properties config  = new Properties();
    protected Properties or  = new Properties();
    String userDir = System.getProperty("user.dir");

    String browser,url;

    @BeforeSuite
    public void setUp()
    {
        if(driver == null)
        {
            System.out.println("User Directory: "+userDir);
            //Get configuration properties
            try {
                fis = new FileInputStream(userDir + "/src/test/resources/properties/config.properties");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                config.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Get object repository properties
            try {
                fis = new FileInputStream(userDir + "/src/test/resources/properties/or.properties");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                or.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            browser = config.getProperty("browser");
            System.out.println("Browser: "+browser);



            //Create instance for Chrome Browser
            System.setProperty("selenium.chrome.driver", userDir + "/resources/drivers/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @AfterSuite
    public void tearDown()
    {
        if(driver != null)
        {
            driver.quit();
        }
    }
}
