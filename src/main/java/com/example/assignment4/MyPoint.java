package com.example.assignment4;

public class MyPoint {
    // x and y coordinate of a cartesian point
    public double x;
    public double y;
    // color of the shape
    public MyColor color;

    // constructors
    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
        this.color = MyColor.NOCOLOR;
    }
    public MyPoint(double x, double y, MyColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public MyPoint() {
        this.x = 0;
        this.y = 0;
        this.color = MyColor.NOCOLOR;
    }
    public MyPoint(MyPoint obj) {
        this(obj.x, obj.y, obj.color);
    }

    // returns distance between two points
    public double calculateDistance(MyPoint p2) {
        return Math.sqrt((p2.y-this.y)*(p2.y-this.y)+(p2.x-this.x)*(p2.x-this.x));
    }

    // returns angle made with x-axis by two points
    public double getXAngle(MyPoint p2) {
        double slope = (this.y - p2.y)/(this.x - p2.x);
        return Math.atan(slope);
    }

}
