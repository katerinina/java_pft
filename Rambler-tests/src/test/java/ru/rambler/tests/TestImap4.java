package ru.rambler.tests;

import javax.mail.*;
import java.util.Properties;


/**
 * Created by user on 28.08.2017.
 */

public class TestImap4 {
    //метод инициализирует сессию с почтовым сервером imap.rambler.ru по протоколу Imap, заходит в папку INBOX
    // и передает кол-во непрочитанных писем
    public static int newMailCount(String username, String password) throws Exception {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        String host = "imap.rambler.ru";
        String provider = "imaps";

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore(provider);
        store.connect(host, username, password);

        Folder inbox = store.getFolder("INBOX");
        if (inbox == null) {
            System.out.println("No INBOX");
            System.exit(1);
        }
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();

        //assertTrue(inbox.getUnreadMessageCount()>0);
//        int msgCount = 0;
//        for (Message message : messages) {
//            msgCount++;
//            Flags flags = message.getFlags();
//            if (!flags.contains(Flags.Flag.SEEN)) {
//                System.out.println(String.format("Unseen Msg #%d", msgCount));
//                System.out.println(message.getSubject());
//            }
//        }

        inbox.close(false);
        store.close();
        return inbox.getUnreadMessageCount();
    }

}


