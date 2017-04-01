package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by user on 25.03.2017.
 */
public class ContactPhoneTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size()==0){
            app.contact().create(new ContactData()
                    .withFirstname("Ekaterina").withMiddlename("G.").withLastname("Samoshkina")
                    .withNickname("katerinina").withCompany("home").withAddress("Mari-El, Yoshkar-Ola")
                    .withEmail("katerinina@ngs.ru").withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhone(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
               .map(ContactPhoneTest::cleaned)
               .collect(Collectors.joining("\n"));
    }


    public  static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
