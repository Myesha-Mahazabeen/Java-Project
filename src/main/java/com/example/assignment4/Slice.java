package com.example.assignment4;

import javafx.scene.canvas.GraphicsContext;

public class Slice extends MyArc{

    private String c;
    private double yPos;
    private int freq;

    public Slice(MyColor color, MyOval o, double startAngle, double angle, String c) {
        super(color, o, startAngle, angle);
        this.c = c;
        this.yPos = 0;
    }

    public Slice(MyColor color, MyPoint center, double h, double w, double startAngle, double angle, String c) {
        this(color, new MyOval(center, h, w), startAngle, angle, c);
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public String getC() {
        return c;
    }

    public void setFreq(int freq){
        this.freq = freq;
    }

    @Override
    void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        graphicsContext.fillText(c+":\t"+freq, 40, yPos);
        graphicsContext.fillOval(10, yPos-15, 20, 20);
    }

    @Override
    public String toString() {
        return "Slice{" +
                "\n\tCharacter: " + this.c +
                "\n\tProbability: " + this.angle/360.0 +
                "\n\tColor: "+ this.color.getHexRepresentation() +
                "\n}";
    }
}