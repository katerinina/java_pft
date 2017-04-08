package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by user on 14.03.2017.
 */
public class ContactModificationTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.db().contacts().size()==0){
        app.contact().create(new ContactData()
                .withFirstname("Ekaterina").withLastname("Samoshkina")
                .withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru").withHomePhone("222555"));
        app.goTo().homePage();
    }
    }

    @Test
    public  void testContactModification(){
        Contacts before = app.db().contacts();
        //возвращает случайный элемент множества
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("M-Ekaterina").withLastname("M-Samoshkina").withMiddlename("M-G.")
                .withAddress("M-Mari-El, Yoshkar-Ola").withEmail("M-katerinina@ngs.ru").withEmail2("M-222@ngs.ru").withEmail3("M-333@ngs.ru")
                .withHomePhone("M-222555").withWorkPhone("M-333333").withMobilePhone("M-444444").withCompany("M-home").withNickname("M-katerinina")
                .withPhoto(new File("src/test/resources/avatar.jpg"));
        app.contact().modify(contact);
        app.goTo().returnHome();
        //хэширование - делается быстрая проверка кол-во контактов после модификации группы
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        System.out.println(before.without(modifiedContact).withAdded(contact));
        //before.without(modifiedContact).withAdded(contact);
        assertThat(after,equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
