package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by user on 14.03.2017.
 */
public class HelperBase {
    protected ApplicationManager app;
    protected WebDriver wd;


    public HelperBase(ApplicationManager app) {
        this.app=app;
        this.wd=app.getDriver();
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }
    protected void type(By locator, String text){
        click(locator);
        if(text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

}
