package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ekaterina").withMiddlename("G.").withLastname("Samoshkina").withNickname("katerinina")
                .withCompany("home").withAddress("Mari-El, Yoshkar-Ola").withEmail("katerinina@ngs.ru");
        app.contact().create(contact);
        app.goTo().returnHome();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1);

        //вычисляется максимальный идентификатор
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before,after);
    }

}
