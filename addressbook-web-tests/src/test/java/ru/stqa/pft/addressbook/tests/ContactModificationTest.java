package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 14.03.2017.
 */
public class ContactModificationTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size()==0){
        app.contact().create(new ContactData()
                .withFirstname("Ekaterina").withMiddlename("G.").withLastname("Samoshkina").withNickname("katerinina")
                .withCompany("home").withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru"));
        app.goTo().homePage();
    }
    }

    @Test
    public  void testContactModification(){
        Set<ContactData> before = app.contact().all();
        //возвращает случайный элемент множества
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Ekaterina").withMiddlename("G.")
                .withLastname("Samoshkina").withNickname("katerinina").withCompany("home")
                .withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru");
        app.contact().modify(contact);
        app.goTo().returnHome();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }
}
