package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 15.04.2017.
 */
public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException{
        long now = System.currentTimeMillis();
        String user = String.format("user%s",now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.registration().start(user, email);
        List<MailMessage> mailMassage= app.mail().waitForMail(2,10000);
        String confirmationLink = findConfirmationLink(mailMassage, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user,password));

    }

    private String findConfirmationLink(List<MailMessage> mailMassage, String email) {
        MailMessage mailMessage = mailMassage.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public  void  stopMailServer(){
        app.mail().stop();
    }
}
