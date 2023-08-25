package com.example.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface MyShapeInterface {
    abstract MyRectangle getMyBoundingRectangle();
    abstract boolean pointInMyShape(MyPoint p);
    public static MyRectangle intersectMyShapes(MyShape s1, MyShape s2){
        MyRectangle rectangle1= s1.getMyBoundingRectangle();
        MyRectangle rectangle2= s2.getMyBoundingRectangle();

        MyPoint tlcp1 = rectangle1.getTLCP();
        MyPoint brcp1 = new MyPoint(tlcp1.x+rectangle1.getWidth(), tlcp1.y+rectangle1.getHeight());

        MyPoint tlcp2 = rectangle2.getTLCP();
        MyPoint brcp2 = new MyPoint(tlcp2.x+ rectangle2.getWidth(), tlcp2.y+rectangle2.getHeight());

        MyPoint n_tlcp=null, n_brcp=null;

        if(rectangle1.pointInMyShape(tlcp2)){
            n_tlcp = tlcp2;
            n_brcp = brcp1;
        }

        if(rectangle1.pointInMyShape(brcp2)) {
            n_brcp = brcp2;
            if(n_tlcp == null) {
                n_tlcp = tlcp1;
            }
        }
        if(n_brcp != null && n_tlcp != null) {
            double h = n_brcp.y - n_tlcp.y;
            double w = n_brcp.x - n_tlcp.x;
            MyRectangle intersection = new MyRectangle(n_tlcp.x+w/2, n_tlcp.y+h/2, h, w);
            return intersection;
        }
        return null;
    }

    public default Canvas drawIntersectMyShape(MyShape shape1, MyShape shape2) {
        MyRectangle intersection = MyShapeInterface.intersectMyShapes(shape1, shape2);
        if(intersection==null) intersection = MyShapeInterface.intersectMyShapes(shape2, shape1);
        Canvas canvas = new Canvas(1000, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(intersection!=null) {
            intersection.draw(gc);
        }
        return canvas;
    }
}