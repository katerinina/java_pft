package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by user on 14.03.2017.
 */
public class ContactDeletionTest extends TestBase {
    @Test
    public  void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ekaterina", "G.", "Samoshkina",
                    "katerinina", "home", "Mari-El, Yoshkar-Ola",
                    "katerinina@ngs.ru"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().acceptContactDeletion();
        app.getNavigationHelper().gotoHomePage();
    }
}
