package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

import java.sql.Driver;

/**
 * Created by user on 15.04.2017.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/singup_page.php");

    }
}
