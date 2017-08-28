package ru.rambler.tests;

import javax.mail.*;
import java.util.Properties;

/**
 * Created by user on 28.08.2017.
 */

public class TestImap4 {

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        String host = "imap.rambler.ru";
//        String username = "samoshkina_ekaterina@rambler.ru";
        String username = "samoshkina_ekaterina";
        String password = "000katerina";
        String provider = "imaps";

//        Session session = Session.getDefaultInstance(props, null);
        Session session = Session.getDefaultInstance(props, null);
//        session.setDebug(true);
        Store store = session.getStore(provider);
        store.connect(host, username, password);

        Folder inbox = store.getFolder("INBOX");
        if (inbox == null) {
            System.out.println("No INBOX");
            System.exit(1);
        }
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
//        inbox.getUnreadMessageCount();
        int msgCount = 0;
        for (Message message : messages) {
            msgCount++;
            Flags flags = message.getFlags();
            if (!flags.contains(Flags.Flag.SEEN)) {
                System.out.println(String.format("Unseen Msg #%d", msgCount));
                System.out.println(message.getSubject());
            }
        }
//        for (int i = 0; i < messages.length; i++) {
//            System.out.println("Message " + (i + 1));
//            messages[i].writeTo(System.out);
//        }
        inbox.close(false);
        store.close();
    }
}


