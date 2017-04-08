package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03.04.2017.
 */
public class ContactDataGenerator {
    @Parameter(names="-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {


        ContactDataGenerator generator= new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCSV(contacts, new File(file));
        } if (format.equals("xml")) {
            saveAsXML(contacts, new File(file));
        }else System.out.println("Unrecognized format "+format);
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        try(FileWriter writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;\n", contact.getFirstname(), contact.getLastname(),
                        contact.getAddress(), contact.getAllEmails(), contact.getAllPhones()));
            }
        }
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        File foto= new File("src/test/resources/avatar.jpg");
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("ContactFirstname %s", i))
                    .withLastname(String.format("ContactLastname %s", i))
                    .withMiddlename("G.")
                    .withNickname(String.format("katerinina%s", i))
                    .withAddress(String.format("ContactAddress %s", i))
                    .withEmail(String.format("email%s@111.ru", i))
                    .withEmail2(String.format("email%s@222.ru", i))
                    .withEmail3(String.format("email%s@333.ru", i))
                    .withHomePhone(String.format("11111%s", i))
                    .withWorkPhone(String.format("22222%s", i))
                    .withMobilePhone(String.format("33333%s", i))
                    .withCompany("Home")
                    .withPhoto(foto));
        }
        return contacts;
    }
}


