package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by user on 14.03.2017.
 */
public class GroupDeletionTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size()==0){
            app.group().create(new GroupData().withName("TestGroup"));
        }
    }

    @Test
    public  void testGroupDeletion(){
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        app.group().delete(index);
        app.goTo().groupPage();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);




    }

}
