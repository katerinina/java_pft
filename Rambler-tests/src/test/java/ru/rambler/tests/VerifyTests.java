package ru.rambler.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class VerifyTests {
    ChromeDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void testVerify() {
        wd.get("https://id.rambler.ru/account/registration");
        wd.findElement(By.id("login.username")).click();
        wd.findElement(By.id("login.username")).clear();
        //wd.findElement(By.id("login.username")).sendKeys("ekaterina_samosh");
        wd.findElement(By.id("login.username")).sendKeys("ekaterina_samoshkina");
        wd.findElement(By.id("password.main")).click();
        wd.findElement(By.id("password.main")).clear();
        wd.findElement(By.id("password.main")).sendKeys("sdfget55677749");
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
