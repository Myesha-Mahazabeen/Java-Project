package com.example.assignment4;

import javafx.scene.canvas.GraphicsContext;
import java.util.*;
import static java.lang.Math.min;

public class HistogramAlphaBet {

    private Map<Character, Integer> freq = new HashMap<>();
    private Map<Character, Double> prob = new HashMap<>();

    public HistogramAlphaBet(Map<Character, Integer> mp) throws Exception {
        this.freq=mp;
        calculateProb();
    }

    public Map<Character, Integer> getFreq() {
        return freq;
    }

    public Map<Character, Double> getProb() {
        return prob;
    }


    private void calculateProb() throws Exception{
        double cc = 0;
        for (Integer i: freq.values()) {
            cc+=i;
        }
        for(Character c:freq.keySet()){
            prob.put(c, freq.get(c)/cc);
        }
    }

    public class MyPieChart {
        private Map<Character, Slice> slices = new HashMap<>();
        public MyPieChart(double w, double h) {
            MyCircle pieChart = new MyCircle(w/2, h/2, min(h, w)*0.5);
            double tot_ang = 0;
            MyColor[] colors = new MyColor[49];
            Slice slice;
            int i = 0;
            for (MyColor color : MyColor.values()) {
                colors[i++] = color;
            }
            i =9;
            for(Character key: prob.keySet()) {
                double angle = 360.0*prob.get(key);
                slice = new Slice(colors[i], pieChart, tot_ang, angle, key+"");
                slices.put(key, slice);
                i++;
                tot_ang += angle;
            }
        }

        public void draw(GraphicsContext graphicsContext) {
            double tot_ang = 0;
            tot_ang=90;
            int textYPos=25;
            for(Character c:slices.keySet()){
                slices.get(c).setyPos(textYPos);
                slices.get(c).startAngle = tot_ang;
                slices.get(c).setFreq(freq.get(c));
                tot_ang += slices.get(c).angle;
                slices.get(c).draw(graphicsContext);
                textYPos+=25;
            }
        }
    }

}