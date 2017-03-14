package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 14.03.2017.
 */
public class ContactDeletionTest extends TestBase {
    @Test
    public  void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().acceptContactDeletion();
        app.getNavigationHelper().gotoHomePage();
    }
}
