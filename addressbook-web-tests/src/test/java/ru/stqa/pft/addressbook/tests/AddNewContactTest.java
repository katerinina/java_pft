package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ekaterina", "G.", "Samoshkina",
                                        "katerinina", "home", "Mari-El, Yoshkar-Ola",
                                        "katerinina@ngs.ru"));
        app.getContactHelper().submitNewContactCreation();
        app.getNavigationHelper().returnHomePage();
    }

}