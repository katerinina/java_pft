package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 01.04.2017.
 */
public class ContactDetailedTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("Ekaterina").withLastname("Samoshkina")
                    .withAddress("Mari-El, Yoshkar-Ola")
                    .withEmail("katerinina@ngs.ru").withEmail2("222@ru.ru").withEmail3("333@com.com")
                    .withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testDetailedContact() {
        //выбираем случайным образом один контакт
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailedForm = app.contact().infoFromDelailedForm(contact);

        //проверка
        assertThat(cleaned(contactInfoFromDetailedForm.getAllDetailes()), equalTo(cleaned(mergeAll(contactInfoFromEditForm))));

    }

    private String mergeAll(ContactData contact) {
        String homePhone = null;
        String mobilePhone = null;
        String workPhone = null;
        if (!contact.getHomePhone().equals("")) {
            homePhone = "H:" + contact.getHomePhone();
        }

        if (!contact.getMobilePhone().equals("")) {
            mobilePhone = "M:" + contact.getMobilePhone();
        }

        if (!contact.getWorkPhone().equals("")) {
            workPhone = "W:" + contact.getWorkPhone();
        }

        return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                homePhone,
                mobilePhone,
                workPhone,
                contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !(s == null || s.equals("")))
                .map(ContactDetailedTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String string) {
        return string.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
