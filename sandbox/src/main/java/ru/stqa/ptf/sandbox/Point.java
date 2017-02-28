package ru.stqa.ptf.sandbox;

/**
 * Created by user on 28.02.2017.
 */
public class Point {
    public int x;
    public int y;

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    public double distance(Point p) {
        return (Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)));
    }
}
