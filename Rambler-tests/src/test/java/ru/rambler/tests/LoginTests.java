package ru.rambler.tests;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 27.08.2017.
 */
public class LoginTests extends HttpSession {


    @Test
    public void testLogin() throws IOException {
        HttpSession session = new HttpSession();
        session.login("samoshkina_ekaterina","000katerina");

        assertTrue(session.login("samoshkina_ekaterina","000katerina"));
    }

}
