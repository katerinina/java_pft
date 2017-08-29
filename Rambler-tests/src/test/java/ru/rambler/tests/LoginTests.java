package ru.rambler.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 27.08.2017.
 */
public class LoginTests extends TestImap4 {

    //тест авторизуется на почту @rambter.ru по указанному логину и паролю и проверяет наличие (непрочитанных) писем
    @Test
    public void testNewMailPresent() throws Exception {
        assertTrue(newMailCount("samoshkina_ekaterina", "000katerina") > 0);

    }

}
