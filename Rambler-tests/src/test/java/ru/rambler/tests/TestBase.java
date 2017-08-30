package ru.rambler.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class TestBase {

    ChromeDriver wd;

    @BeforeClass
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    //переход на страницу регистрации Rambler
    public void goToRegistrationPage() {
        if (isElementPresent(By.xpath("//div[1]/div/div/header/h1"))) {
            return;
        }
        wd.get("https://id.rambler.ru/account/registration");

    }

    //переход на страницу авторизации почты mail.rambler.ru
    public void goToMailPage() {
        if (isElementPresent(By.xpath("//h2[@class='form__title']//span[.='Войти в почту']"))) {
            return;
        }
        wd.get("https://mail.rambler.ru");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //проверка присутствия элемента на странице
    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    //заполнение полей регистрации нового пользователя
    protected void addNewUserName(String username, String domain) {
        type(By.id("login.username"), username);
        click(By.name("login.domain"));
        click(By.xpath("//div[@class='menu-3152310865']//div[.='@" + domain + "']"));
    }
}
