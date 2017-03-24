package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 14.03.2017.
 */
public class ContactModificationTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()){
        app.getContactHelper().createContact(new ContactData("Ekaterina", "G.", "Samoshkina",
                "katerinina", "home", "Mari-El, Yoshkar-Ola",
                "katerinina@ngs.ru"));
        app.getNavigationHelper().gotoHomePage();
    }
    }

    @Test
    public  void testContactModification(){
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()-1;
        ContactData contact = new ContactData(before.get(index).getId(),"Ekaterina", "G.", "Samoshkina",
                "katerinina", "home", "Mari-El, Yoshkar-Ola",
                "katerinina@ngs.ru");
        app.getContactHelper().modifyContact(index, contact);
        app.getNavigationHelper().returnHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
