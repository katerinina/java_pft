package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 14.03.2017.
 */
public class GroupDeletionTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("TestGroup"));
        }
    }

    @Test
    public  void testGroupDeletion(){
        Set<GroupData> before = app.group().all();
        //возвращает случайный элемент множества
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);




    }

}
