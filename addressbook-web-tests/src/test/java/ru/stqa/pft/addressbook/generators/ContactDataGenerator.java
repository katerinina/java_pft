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
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s%s%s%s%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getAddress(), contact.getAllEmails(), contact.getAllPhones()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("ContactFirstname %s;", i))
                    .withLastname(String.format("ContactLastname %s;", i))
                    .withAddress(String.format("ContactAddress %s;", i))
                    .withAllEmails(String.format("email%s@ggg.ru;", i))
                    .withAllPhones(String.format("99999%s;", i)));
        }
        return contacts;
    }
}


