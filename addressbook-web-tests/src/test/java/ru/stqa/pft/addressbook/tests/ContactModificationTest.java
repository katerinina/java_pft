package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
        Contacts before = app.contact().all();
        //возвращает случайный элемент множества
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Ekaterina").withMiddlename("G.")
                .withLastname("Samoshkina").withNickname("katerinina").withCompany("home")
                .withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru");
        app.contact().modify(contact);
        app.goTo().returnHome();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
