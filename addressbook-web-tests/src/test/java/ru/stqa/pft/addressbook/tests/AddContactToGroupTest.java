package ru.stqa.pft.addressbook.tests;

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
public class AddContactToGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {

            app.contact().create(new ContactData().withFirstname("Firstname").withLastname("Lastname")
                    .withAddress("Home")
                    .withHomePhone("89023261742").withEmail("eee@ggg.ru")
                    .inGroup(groups.iterator().next()));
            app.goTo().homePage();
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("rtest1").withHeader("rtest2"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testAddContactToGroup(){
        Contacts before = app.db().contacts();
        app.goTo().homePage();
        Groups group = app.db().groups();
        ContactData modifiedContact = before.iterator().next();
        GroupData addedGroup = group.iterator().next();
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().addContactToGroup(addedGroup.getId());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
    }
}
