package com.example.assignment4;

import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape{
    // height and width of the oval
    private double h, w;

    // constructors
    public MyOval(MyPoint refPoint, double h, double w) {
        super(refPoint);
        this.h = h;
        this.w = w;
    }
    public MyOval(MyPoint refPoint, MyColor color, double h, double w) {
        super(refPoint, color);
        this.h = h;
        this.w = w;
    }
    public MyOval(double h, double w) {
        super();
        this.h = h;
        this.w = w;
    }
    public MyOval(MyColor color, double h, double w) {
        super(color);
        this.h = h;
        this.w = w;
    }
    public MyOval(double x, double y, double h, double w) {
        super(x, y);
        this.h = h;
        this.w = w;
    }
    public MyOval(double x, double y, MyColor color, double h, double w) {
        super(x, y, color);
        this.h = h;
        this.w = w;
    }
    public MyOval(MyOval obj) {
        this(obj.p, obj.color, obj.h, obj.w);
    }

    public MyPoint getCenter() {
        return this.p;
    }
    public double getWidth(){
        return this.w;
    }
    public double getHeight(){
        return this.h;
    }

    // get semi major and semi minor axis
    public double getMinorAxis() {
        return Math.min(this.h, this.w)/2;
    }
    public double getMajorAxis() {
        return Math.max(this.h, this.w)/2;
    }

    // close estimation of perimeter calculated
    @Override
    public double perimeter() {
        double s = ((h-w)*(h-w))/((h+w)*(h+w));
        double p =  Math.PI * (h+w) * (1 + s/4 +
                Math.pow(s, 2)/64 +
                Math.pow(s, 3)/256 +
                25*Math.pow(s, 4)/16384 +
                49*Math.pow(s, 5)/65536 +
                441*Math.pow(s, 6)/1048576);
        return p;
    }

    // area of oval
    @Override
    public double area() {
        return Math.PI * this.h/2 * this.w/2;
    }

    @Override
    public String toString() {
        return "MyOval{" +
                "\n\tsemi minor axis=" + this.getMinorAxis() +
                "\n\tsemi major axis=" + this.getMajorAxis() +
                "\n\tperimeter=" + this.perimeter() +
                "\n\tarea=" + this.area() +
                "\n}";
    }

    // draw the oval
    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.color.getColor());
        graphicsContext.fillOval(this.p.x-this.w/2, this.p.y-this.h/2, this.w, this.h);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle boundingRectangle = new MyRectangle(this.p, this.h,this.w);
        return boundingRectangle;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        double chk = (Math.pow(p.x-this.p.x,2)/Math.pow(this.w/2,2))+(Math.pow(p.y-this.p.y,2)/Math.pow(this.h/2,2));
        if(chk<=1){
            return true;
        }
        else{
            return false;
        }
    }
}
