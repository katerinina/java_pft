package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by user on 14.03.2017.
 */
public class ContactDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.db().contacts().size()==0){
            app.contact().create(new ContactData()
                            .withFirstname("Ekaterina").withMiddlename("G.").withLastname("Samoshkina")
                            .withNickname("katerinina").withCompany("home").withAddress("Mari-El, Yoshkar-Ola")
                            .withEmail("katerinina@ngs.ru"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        //возвращает случайный элемент множества
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        //хэширование - делается быстрая проверка кол-во контактов после удаления группы
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));

    }

}
