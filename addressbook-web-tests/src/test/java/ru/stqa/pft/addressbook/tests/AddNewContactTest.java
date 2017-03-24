package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("Ekaterina", "G.", "Samoshkina",
                "katerinina", "home", "Mari-El, Yoshkar-Ola",
                "katerinina@ngs.ru");
        app.contact().create(contact);
        app.goTo().returnHome();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()+1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
