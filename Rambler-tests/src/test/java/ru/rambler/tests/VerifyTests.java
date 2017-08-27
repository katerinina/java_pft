package ru.rambler.tests;


import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VerifyTests extends TestBase{

    @Test
    public void testVerifyUserNameAndPassword() {
        type(By.id("login.username"), "ekaterina_samosh");
        type(By.id("password.main"),"6574hfjruyvbnblogu");
        assertFalse(isElementPresent(By.xpath("//div[1]/div/div/form/section[3]/div/div/div[2]")));
        assertTrue(isElementPresent(By.xpath("//div[@class='success-2143439648']//div[.='Сложный пароль']")));
    }


}
