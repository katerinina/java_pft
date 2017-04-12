package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by user on 12.04.2017.
 */
public class DeleteContactFromGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePrecondition() {

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {

            app.contact().create(new ContactData().withFirstname("Firstname").withLastname("Lastname")
                    .withAddress("address 80 / 5")
                    .withHomePhone("89023261742").withEmail("e-mail@mail.ru")
                    .inGroup(groups.iterator().next()));
            app.goTo().homePage();
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1").withHeader("Header"));
        }
        for(ContactData contact : contacts) {
            if (contact.getGroups().size() == 0) {
                app.goTo().homePage();
                ContactData modifiedContact = contacts.iterator().next();
                GroupData addedGroup = groups.iterator().next();
                app.contact().selectContactById(modifiedContact.getId());
                app.contact().addContactToGroup(addedGroup.getId());
            }
        }
    }

    @Test
    public void testDeleteContactFromGroup(){

        Contacts before = app.db().contacts();
        app.goTo().homePage();
        ContactData modifiedContact = before.iterator().next();
        GroupData removeGroup = modifiedContact.getGroups().iterator().next();
        app.contact().removeContactFromGroup(removeGroup.getId());
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().click(By.cssSelector("input[name='remove']"));
        Contacts after = app.db().contacts();
        after.remove(modifiedContact);
        ContactData modifiedContact2 = new ContactData();
        for(ContactData contact : before) {
            if (contact.equals(modifiedContact)) {
                contact.getGroups().remove(removeGroup);
                after.add(contact);
            }
        }
        modifiedContact2.getGroups().remove(removeGroup);
        System.out.println(modifiedContact2);

        assertThat(after, equalTo(before));
    }

}
