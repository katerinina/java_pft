package ru.rambler.tests;

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
import ru.rambler.tests.TestBase;

import static org.openqa.selenium.OutputType.*;

public class AuthorizationTest extends TestBase{

    @Test
    public void testAuthorizationToMail() {
        //переход на страницу авторизации на почтовый сервер
        goToMailPage();
        //заполение полей авторизации
        type(By.name("login"),"samoshkina_ekaterina");
        type(By.id("form_password"),"000katerina");
        click(By.xpath("//form[@class='form']/div[1]/div[2]/div/select//option[1]"));
        //нажатие на кнопку "Войти на почту"
        click(By.cssSelector("button.form-button.form-button_submit"));
        //проверка, что действительно находимся на странице почты нужного пользователя
        //assertTrue(isElementPresent(By.xpath("//span[@class='rambler-topline__user-name']//div[.='Ekaterina Samoshkina'")));
//                //div[@class='error-1781068642']//div[.='Пользователь уже существует']
//                body.contains(String.format("<span class=\"rambler-topline__user-name\">Ekaterina Samoshkina</span>");
//

        //wd.findElement(By.cssSelector("span.style-counter_2zl")).click();
    }
    
    }
