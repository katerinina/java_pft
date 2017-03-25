package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ekaterina").withMiddlename("G.").withLastname("Samoshkina").withNickname("katerinina")
                .withCompany("home").withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru");
        app.contact().create(contact);
        app.goTo().returnHome();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()+1));
        //вычисляется максимальный идентификатор
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
