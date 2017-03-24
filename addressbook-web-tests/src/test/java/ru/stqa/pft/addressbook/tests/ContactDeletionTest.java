package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 14.03.2017.
 */
public class ContactDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size()==0){
            app.contact().create(new ContactData()
                            .withFirstname("Ekaterina").withMiddlename("G.").withLastname("Samoshkina")
                            .withNickname("katerinina").withCompany("home").withAddress("Mari-El, Yoshkar-Ola")
                            .withEmail("katerinina@ngs.ru"));
            app.goTo().homePage();
        }
    }
    @Test
    public  void testContactDeletion(){
        Set<ContactData> before = app.contact().all();
        //возвращает случайный элемент множества
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
