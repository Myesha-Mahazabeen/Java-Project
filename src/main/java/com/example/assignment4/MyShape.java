package com.example.assignment4;

import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapeInterface {
    public MyPoint p;
    public MyColor color;

    // constructors
    public MyShape(MyPoint p) {
        this.p = p;
        color = MyColor.BLACK;
    }
    public MyShape(MyPoint p, MyColor color) {
        this.p = p;
        this.color = color;
    }
    public MyShape() {
        this.p = new MyPoint();
        color = MyColor.BLACK;
    }
    public MyShape(MyColor color) {
        this.p = new MyPoint();
        this.color = color;
    }
    public MyShape(double x, double y) {
        this.p = new MyPoint(x, y);
        this.color = MyColor.BLACK;
    }
    public MyShape(double x, double y, MyColor color) {
        this.p = new MyPoint(x, y);
        this.color = color;
    }
    public MyShape(MyShape obj1) {
        this(obj1.p, obj1.color);
    }

    //  returns perimeter and area, meant to be overridden
    abstract double perimeter() ;
    abstract double area() ;

    // string description of MyShape containing MyPoint p
    @Override
    public String toString() {
        return "MyShape {" +
                "\n\tp: (" + p.x +
                ", "+ p.y+")\n}";
    }

    // changes background color
    abstract void draw(GraphicsContext graphicsContext) ;

}
