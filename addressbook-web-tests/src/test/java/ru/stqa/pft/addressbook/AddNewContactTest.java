package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        gotoHomePage();
        initContactCreation();
        fillContactForm(new ContactData("Ekaterina", "G.", "Samoshkina",
                                        "katerinina", "home", "Mari-El, Yoshkar-Ola",
                                        "katerinina@ngs.ru"));
        submitNewContactCreation();
        gotoHomePage();
    }

}
