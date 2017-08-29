package ru.rambler.tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FieldsVerifyTests extends TestBase {
    //Позитивные тесты для проверки заполнения полей
    //тест для проверки заполнения поля "Почтовый ящик" несуществующим логином
    @Test
    public void testVerifyUserName() {
        addUserName("ekaterina_samoshkina","lenta.ru");
        //чтобы произошла валидация, необходимо снять фокус с поля(особенность Selenium),поэтому кликаю на предыдущее поле
        click(By.id("login.username"));
        assertFalse(isElementPresent(By.xpath("//div[@class='error-1781068642']//div[.='Пользователь уже существует']")));
    }

    //тест для проверки заполнения полей "пароль" и "повтор пароля"
    @Test
    public void testVerifyPassword() {
        type(By.id("password.main"), "6574hfjruyvbnblogu");
        type(By.id("password.confirm"), "6574hfjruyvbnblogu");
        //чтобы произошла валидация, необходимо снять фокус с поля(особенность Selenium),поэтому кликаю на предыдущее поле
        click(By.id("password.main"));
        assertTrue(isElementPresent(By.xpath("//div[@class='success-2143439648']//div[.='Сложный пароль']")));
        assertTrue(isElementPresent(By.xpath("//div[@class='success-2143439648']//div[.='Введено верно']")));
    }
    //негативные тесты для проверки заполнения полей
    //тест для проверки заполнения поля "Почтовый ящик" уже существующим логином
    @Test
    public void testVerifyInvalidUserName() {
        addUserName("ekaterina_samoshkina","rambler.ru");
        //чтобы произошла валидация, необходимо снять фокус с поля(особенность Selenium),поэтому кликаю на предыдущее поле
        click(By.id("login.username"));
        assertTrue(isElementPresent(By.xpath("//div[@class='error-1781068642']//div[.='Пользователь уже существует']")));
    }
    //тест для проверки заполнения полей "пароль" и "повтор пароля" несовпадающими значениями
    @Test
    public void testVerifyDifferentPassword() {
        type(By.id("password.main"), "6574hfjruybnblogu123");
        type(By.id("password.confirm"), "6574hfjruyvnblogu1234678976");
        //чтобы произошла валидация, необходимо снять фокус с поля(особенность Selenium),поэтому кликаю на предыдущее поле
        click(By.id("password.main"));
        assertTrue(isElementPresent(By.xpath("//div[@class='success-2143439648']//div[.='Сложный пароль']")));
        assertTrue(isElementPresent(By.xpath("//div[@class='error-1781068642']//div[.='Пароли не совпадают']")));

    }


}
