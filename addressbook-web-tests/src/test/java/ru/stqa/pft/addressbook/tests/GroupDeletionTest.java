package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

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
        Groups before = app.group().all();
        //возвращает случайный элемент множества
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        //хэширование - делается быстрая проверка кол-во групп после удаления группы
        assertThat(app.group().count(),equalTo(before.size()-1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));




    }

}
