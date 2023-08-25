package com.example.assignment4;

public class MyCircle extends MyOval{
    public MyCircle(MyPoint center, double r){
        super(center,r,r);
    }
    public MyCircle(MyPoint center, double r, MyColor c){
        super(center,c,r,r);
    }
    public MyCircle(double x,double y, double r){
        super(x,y,r,r);
    }
    public MyCircle(double x,double y, double r, MyColor c ){
        super(x,y,c,r,r);
    }

    @Override
    public double area() {
        return Math.PI*Math.pow(this.getHeight(),2);
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*this.getMajorAxis();
    }
    @Override
    public String toString() {
        return "MyCircle{" +
                "\n\tcenter= ("+this.p.x+", "+this.p.y+")"+
                "\n\tradius=" + this.getMinorAxis() +
                "\n\tperimeter=" + this.perimeter() +
                "\n\tarea=" + this.area() +
                "\n}";
    }
}
//blah