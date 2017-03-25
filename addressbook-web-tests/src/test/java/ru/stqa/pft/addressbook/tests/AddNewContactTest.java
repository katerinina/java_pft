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
                .withCompany("home").withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru")
                .withHomePhone("111").withMobilePhone("222").withWorkPhone("333");
        app.contact().create(contact);
        app.goTo().returnHome();
        //хэширование - делается быстрая проверка кол-во контактов после создания новой группы
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        //вычисляется максимальный идентификатор
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
