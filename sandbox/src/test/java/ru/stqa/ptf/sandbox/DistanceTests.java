package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 28.02.2017.
 */
public class DistanceTests {
    @Test
    public void testDistance(){
        Point p1=new Point(1,1);
        Point p2=new Point (4,5);
       //тест должен выполняться без ошибок
        Assert.assertEquals(p1.distance(p2),5,00);
        //тест выдает ошибку
        Assert.assertEquals(p1.distance(p2),4,00);

    }
}
