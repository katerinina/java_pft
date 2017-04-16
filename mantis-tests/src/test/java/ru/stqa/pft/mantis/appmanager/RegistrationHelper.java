package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by user on 15.04.2017.
 */
public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/singup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("span[class='bigger-110']"));
    }
    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public void addNewUser() throws IOException {
        Long now = System.currentTimeMillis();
        String email = String.format("user1%s@localhost.localdomain", now);
        String user = "user1" + now;
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }
    public void loginAdministrator() {
        wd.manage().window().maximize();
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), "administrator");
        type(By.name("password"), "root1");
        click(By.cssSelector("input[value='Войти']"));
    }

}
