package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by user on 16.04.2017.
 */
public class ChangePasswordTest extends TestBase{
    @BeforeMethod
    public void ensurePrecondition() throws IOException {

        app.mail().start();


        if (app.db().users().size() == 0){
            app.registration().addNewUser();
        }
    }

    @Test
    public void testChangePasswordOfUser() throws IOException {

        String password = String.format("password" + System.currentTimeMillis());
        HashSet<UserData> before = app.db().users();
        UserData modifiedUser = before.iterator().next();
        app.registration().loginAdministrator();
        app.registration().click(By.cssSelector("i.fa-gears"));
        app.registration().click(By.linkText("Управление пользователями"));
        app.registration().click(By.linkText(modifiedUser.getName()));
        app.registration().click(By.cssSelector("input[value='Сбросить пароль']"));
        String f = modifiedUser.getEmail();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 15000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, modifiedUser.getEmail());
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(modifiedUser.getName(), password));

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
