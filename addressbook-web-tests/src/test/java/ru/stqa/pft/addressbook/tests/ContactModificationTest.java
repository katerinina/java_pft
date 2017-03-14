package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by user on 14.03.2017.
 */
public class ContactModificationTest extends TestBase{

    @Test
    public  void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ekaterina", "G.", "Samoshkina",
                "katerinina", "home", "Mari-El, Yoshkar-Ola",
                "katerinina@ngs.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();
    }
}
