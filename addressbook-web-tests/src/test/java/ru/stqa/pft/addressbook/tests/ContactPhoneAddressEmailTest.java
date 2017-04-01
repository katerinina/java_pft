package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 25.03.2017.
 */
public class ContactPhoneAddressEmailTest extends TestBase{
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
    public void testContactPhoneAddressEmail(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        //проверка телефонов
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        //проверка адреса
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
        //проверка e-mail
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneAddressEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneAddressEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public  static String cleaned(String string){
        return string.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
