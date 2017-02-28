package ru.stqa.ptf.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);

        //функция, которая вычисляет расстояние между двумя точками
        System.out.println("Расстояние между точками с координатами ("+p1.x+","+p1.y+") и ("+p2.x+","+p2.y+") равно "+ distance(p1,p2));

        //та же функция, вычисляет расстояние при помощи метода в классе Point
        //вот только как сделать, чтобы в неё одновременно подавать две разные переменные? где-то ошибка...мой пример не работает
        System.out.println("Расстояние между точками с координатами ("+p1.x+","+p1.y+") и ("+p2.x+","+p2.y+") равно "+ p2.distance(p1));

    }

    public static double distance(Point p1,Point p2) {
        return (Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));
    }


}

