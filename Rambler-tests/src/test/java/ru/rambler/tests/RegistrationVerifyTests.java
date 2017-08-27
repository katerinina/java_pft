package ru.rambler.tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegistrationVerifyTests extends TestBase {

    @Test
    public void testVerifyUserName() {
        type(By.id("login.username"), "ekaterina_samosh");
        assertFalse(isElementPresent(By.xpath("//div[1]/div/div/form/section[3]/div/div/div[2]")));

    }

    @Test
    public void testVerifyPassword() {
        type(By.id("password.main"), "6574hfjruyvbnblogu");
        assertTrue(isElementPresent(By.xpath("//div[@class='success-2143439648']//div[.='Сложный пароль']")));
    }


}
