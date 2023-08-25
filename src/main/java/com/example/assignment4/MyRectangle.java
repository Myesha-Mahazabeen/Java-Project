package com.example.assignment4;

import javafx.scene.canvas.GraphicsContext;

public class MyRectangle extends MyShape{
    // height and width of rectangle
    private double h, w;

    // constructors
    public MyRectangle(MyPoint refPoint, double h, double w) {
        super(refPoint);
        this.h = h;
        this.w = w;
    }
    public MyRectangle(MyPoint refPoint, MyColor color, double h, double w) {
        super(refPoint, color);
        this.h = h;
        this.w = w;
    }
    public MyRectangle(double h, double w) {
        super();
        this.h = h;
        this.w = w;
    }
    public MyRectangle(MyColor color, double h, double w) {
        super(color);
        this.h = h;
        this.w = w;
    }
    public MyRectangle(double x, double y, double h, double w) {
        super(x, y);
        this.h = h;
        this.w = w;
    }
    public MyRectangle(double x, double y, MyColor color, double h, double w) {
        super(x, y, color);
        this.h = h;
        this.w = w;
    }
    public MyRectangle(MyRectangle obj) {
        this(obj.p, obj.color, obj.h, obj.w);
    }

    // get height, width, top left corner point
    public double getHeight() {
        return this.h;
    }
    public double getWidth() {
        return this.w;
    }
    public MyPoint getTLCP() {
        double x = this.p.x - this.w/2;
        double y = this.p.y - this.h/2;
        MyPoint tclp = new MyPoint(x, y);
        return tclp;
    }

    //perimeter and area of rectangle
    @Override
    public double perimeter() {
        return 2 * (this.h + this.w);
    }
    @Override
    public double area() {
        return (this.h * this.w);
    }

    @Override
    public String toString() {
        return "MyRectangle{" +
                "\n\ttop left corner: ("+(this.p.x - this.w/2)+
                ", "+(this.p.y + this.h/2)+")"+
                "\n\theight=" + h +
                "\n\twidth=" + w +
                "\n\tperimeter="+this.perimeter()+
                "\n\tarea="+this.area()+
                "\n}";
    }

    // draw rectangle
    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.color.getColor());
        graphicsContext.fillRect(this.p.x-this.w/2, this.p.y-this.h/2, this.w, this.h);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        return this;
    }
    public boolean pointInMyShape(MyPoint p){
        MyPoint tlcp= this.getTLCP();
        double x2= tlcp.x+this.w;
        double y2=tlcp.y+this.h;
        if (p.x>=tlcp.x && p.x<=x2 && p.y>=tlcp.y && p.y<=y2){
            return true;
        }
        else{
            return false;
        }
    }
}