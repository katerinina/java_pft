package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 14.03.2017.
 */
public class ContactDeletionTest extends TestBase {
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
    public  void testContactDeletion(){
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().acceptContactDeletion();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
