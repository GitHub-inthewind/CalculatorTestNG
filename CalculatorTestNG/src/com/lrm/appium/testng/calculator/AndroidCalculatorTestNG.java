package com.lrm.appium.testng.calculator;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidCalculatorTestNG {
    private AppiumDriver<AndroidElement> driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "Calculator.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // project can run,even if the settings of this following statement is wrong
        capabilities.setCapability("deviceName","0a733f0b");
        capabilities.setCapability("platformVersion", "4.4.4");
        // set the APK's path
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    
    @Test(groups = {"funcAdd"}, invocationCount = 1)
    public void add(){
        
    	WebElement num1 = driver.findElementById("digit_1");
    	num1.click();
    	WebElement add = driver.findElementById("op_add");
    	add.click();
    	WebElement num2 = driver.findElementById("digit_2");
    	num2.click();
    	WebElement eq = driver.findElementById("eq");
    	eq.click();
    	WebElement result = driver.findElementById("result");
    	
    	boolean right = result.getText().equals("2");
    	if(!right)
    	{
    		System.out.println("result is error,start snap shot");
    		snapshot((TakesScreenshot)driver, "error.png");
    		System.out.println("after snap shot");
			try {
				Thread.sleep(10 * 1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("after snap shot,sleep 10s");
    	}
    	else
    	{
    		System.out.println("result is right");
		}
    	
    	System.out.println("before Assert.assertEquals");
    	Assert.assertTrue(right);
    	// if assert is failure,the following statement can not run
    	System.out.println("after Assert.assertEquals");
    }

    @Test(groups = {"funcSub"})
    public void sub(){
    	WebElement num1 = driver.findElementById("digit_1");
    	num1.click();
    	WebElement add = driver.findElementById("op_sub");
    	add.click();
    	WebElement num2 = driver.findElementById("digit_2");
    	num2.click();
    	WebElement eq = driver.findElementById("eq");
    	eq.click();
    }
    
    /** 
     * This Method create for take screenshot 
     *  
     * @param drivername 
     * @param filename 
     */
    public static void snapshot(TakesScreenshot drivername, String filename) { 
        // this method will take screen shot ,require two parameters ,one is 
        // driver name, another is file name 
  
    	// get current work folder 
        String currentPath = System.getProperty("user.dir"); 
        File scrFile = drivername.getScreenshotAs(OutputType.FILE); 
        // Now you can do whatever you need to do with it, for example copy 
        // somewhere 
        try { 
            System.out.println("save snapshot path is:" + currentPath + "\\"
                    + filename); 
            FileUtils 
                    .copyFile(scrFile, new File(currentPath + "\\" + filename)); 
        } catch (IOException e) { 
            System.out.println("Can't save screenshot"); 
            e.printStackTrace(); 
        } finally { 
            System.out.println("screen shot finished, it's in " + currentPath 
                    + " folder"); 
        } 
    } 
  
    
}
