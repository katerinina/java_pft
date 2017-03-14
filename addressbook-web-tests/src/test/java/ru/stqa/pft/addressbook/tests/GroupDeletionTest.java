package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 14.03.2017.
 */
public class GroupDeletionTest extends TestBase{
    @Test

    public  void testGroupDeletion(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();

    }
}
