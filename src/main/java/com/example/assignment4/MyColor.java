package com.example.assignment4;

import javafx.scene.paint.Color;

public enum MyColor {

    ALICEBLUE(240, 248, 255, 255),
    ANTIQUEWHITE(250, 235, 215, 255),
    AQUA(0, 255, 255, 255),
    AQUAMARINE(127, 255, 212, 244),
    AZURE(240, 255, 255, 255),
    BEIGE(245, 245, 220, 255),
    BISQUE(255, 228, 196, 255),
    BLACK (0, 0, 0, 255),
    BLANCHEDAUMOND(255, 235, 205, 255),
    BLUE(0, 0, 255, 255),
    BLUEVIOLET(238, 43, 226, 255),
    BROWN (165, 42, 42, 255),
    BURLYWOOD(222, 184, 135, 255),
    CADETBLUE(95, 158, 160, 255),
    CHARTREUSE(127, 255, 0, 255),
    CHOCOLATE(210, 105, 30, 255),
    CORAL(255, 127, 80, 255),
    CORNFLOWERBLUE(100, 149, 237, 255),
    CORNSILK(255, 248, 220, 255),
    CRIMSON(220, 20, 60, 255),
    CYAN(0, 255, 255, 255),
    DARKBLUE(0, 0, 13, 255),
    DARKCYAN(0, 139, 139, 255),
    DARKGOLDENROD(184, 134, 11, 255),
    DARKGREY(169, 169, 169, 255),
    DARKGREEN(0, 100, 0, 255),
    DARKKHAKI(189, 183, 107, 255),
    DARKMAGENTA(139, 0, 139, 255),
    DARKOLIVEGREEN(85, 107, 47, 255),
    DARKORANGE(255, 140, 0, 255),
    DARKORCHID(153, 50, 204, 255),
    DARKRED(139, 0, 0, 255),
    DARKSALMON(233, 150, 122, 255),
    DARKSEAGREEN(143, 188, 143, 255),
    DARKSLATEBLUE(72, 61, 139, 255),
    DARKSLATEGRAY(47, 79, 79, 255),
    DARKTURQUOISE(0, 206, 209, 255),
    DARKVIOLET(148, 0, 211, 255),
    DARKPINK(255, 20, 147, 255),
    DARKSKYBLUE(0, 191, 255, 255),
    DARKGRAY(105, 105, 105, 255),

    LAVANDER (215, 180, 243, 255),
    YELLOW (255, 255, 0, 255),
    SKYBLUE (0, 181, 226, 255),
    LIME (199, 234, 70, 255),
    WHITE(255, 255, 255, 255),
    RED (255, 0, 0, 255),
    GREEN(0, 255, 0, 255),

    NOCOLOR(0, 0, 0, 0);

    private int r, g, b, opacity;

    // Default constructor
    MyColor() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.opacity = 255;
    }
    // All argument constructors
    MyColor(int r, int g, int b, int opacity) {
        if(r<0 || r>255) { r = 0; }
        if(g<0 || g>255) { g = 0; }
        if(b<0 || b>255) { b = 0; }
        if(opacity<0 || opacity>255) { opacity = 0; }
        this.r = r;
        this.g = g;
        this.b = b;
        this.opacity = opacity;
    }

    // return hexadecimal value of the color specified by MyColor
    public String getHexRepresentation() {
        String hex = "#";
        hex += Integer.toHexString(r);
        hex += Integer.toHexString(g);
        hex += Integer.toHexString(b);

        return hex;
    }

    // returns javaFX representation of MyColor
    public Color getColor() {
        return Color.rgb(r, g, b, opacity/255.0);
    }
}