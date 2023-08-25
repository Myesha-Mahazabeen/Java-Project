package com.example.assignment4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape{
    public MyOval o;
    public double startAngle;
    public double angle;

    public MyArc(MyColor color, MyOval o, double startAngle, double angle) {
        super(color);
        this.o = o;
        this.startAngle = startAngle;
        this.angle = angle;

        double h=this.o.getHeight(), w=this.o.getWidth();
        double ang1=Math.toRadians(startAngle);
        double sqrt = Math.sqrt(Math.pow(h, 2) +
                Math.pow(w * Math.tan(ang1), 2));
        double x = this.o.p.x+(h*w)/ sqrt;
        double y = this.o.p.y+(w*h*Math.tan(ang1))/ sqrt;

        this.p = new MyPoint(x, y);
    }

    public MyArc(MyOval o, double startAngle, double angle) {
        super();
        this.o = o;
        this.startAngle = startAngle;
        this.angle = angle;

        double h=this.o.getHeight(), w=this.o.getWidth();
        double ang1=Math.toRadians(startAngle);
        double sqrt = Math.sqrt(Math.pow(h,2) +
                Math.pow(w*Math.tan(ang1), 2));
        double x = this.o.p.x+(h*w)/sqrt;
        double y = this.o.p.y+(w*h*Math.tan(ang1))/sqrt;

        this.p = new MyPoint(x, y);
    }

    public MyArc(MyArc arc) {
        this(arc.color, arc.o, arc.startAngle, arc.angle);
    }

    @Override
    double perimeter() {
        double h=this.o.getHeight(), w=this.o.getWidth();
        double ang2= Math.toRadians(startAngle +angle);
        double x, y;
        MyPoint p1 = this.p;
        double len = p1.calculateDistance(this.o.p);
        x = this.o.p.x+(h*w)/Math.sqrt(Math.pow(h,2) +
                Math.pow(w*Math.tan(ang2), 2));
        y = this.o.p.y+(w*h*Math.tan(ang2))/Math.sqrt(Math.pow(h, 2) +
                Math.pow(w*Math.tan(ang2), 2));
        MyPoint p2 = new MyPoint(x, y);
        len = len + p2.calculateDistance(this.o.p);

        return len+this.length();
    }

    @Override
    double area() {
        double h=this.o.getHeight(), w=this.o.getWidth();
        double ang1=Math.toRadians(startAngle), ang2= Math.toRadians(startAngle +angle), ang3=Math.toRadians(angle);

        double area = 0.5*h*w*(ang3-
                (Math.atan((h-w)*Math.sin(2.0*ang2))/ ((h+w)+(h-w)*Math.cos(2*ang2)))-
                Math.atan((h-w)*Math.sin(2*ang1))/((h+w)+(h-w)*Math.cos(2*ang1)));
        return area;
    }

    public String toString(){
        return "MyArc{" +
                "\n\tStart Angle= " + this.startAngle +
                "\n\tBody Angle=" + this.angle +
                "\n\tLength= "+this.length()+
                "\n\tperimeter=" + this.perimeter() +
                "\n\tarea=" + this.area() +
                "\n\tstart point: ("+this.p.x+", "+this.p.y+")"+
                "\n\tAssociated Oval: "+this.o.toString()+
                "\n}";
    }
    @Override
    void draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(this.color.getColor());
        graphicsContext.fillArc(this.o.p.x-this.o.getWidth()/2,this.o.p.y-this.o.getHeight()/2,
                this.o.getWidth(),this.o.getHeight(), startAngle, angle, ArcType.ROUND);
    }
    public double length(){
        double h=this.o.getHeight(), w=this.o.getWidth();
        double ang2= Math.toRadians(startAngle +angle);
        double x, y;
        MyPoint p1 = this.p;

        x = this.o.p.x+(h*w)/Math.sqrt(Math.pow(h,2) +
                Math.pow(w*Math.tan(ang2), 2));
        y = this.o.p.y+(w*h*Math.tan(ang2))/Math.sqrt(Math.pow(h, 2) +
                Math.pow(w*Math.tan(ang2), 2));
        MyPoint p2 = new MyPoint(x, y);
        return 0.5 * Math.PI/Math.sqrt(2)*p1.calculateDistance(p2);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return this.o.getMyBoundingRectangle();
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        if(this.o.pointInMyShape(p)) {
            double ang = this.o.p.getXAngle(p);
            ang = Math.toDegrees(ang);
            if(ang >= startAngle && ang <= startAngle +angle){
                return true;
            }
        }
        return false;
    }
}
