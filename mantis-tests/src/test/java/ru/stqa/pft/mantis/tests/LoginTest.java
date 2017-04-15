package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 15.04.2017.
 */
public class LoginTest extends TestBase {
    @Test
    public  void  testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator","root"));
        assertTrue(session.IsLoggedInAs("administrator"));

    }
}
