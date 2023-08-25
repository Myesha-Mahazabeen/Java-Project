package com.example.assignment4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Map;
import java.util.Random;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int n = 50;

        Database db = new Database();
        Database.Schedule scheduleTab = db.new Schedule();
        String q = "set foreign_key_checks=0;";
        ClassScheduleInterface.executeUpdate(db.getCon(), q);
        String[][] students = generateStudents(n);
        Database.Students studentsTab = db.new Students(students);
        Database.Courses coursesTab = db.new Courses();
        String[][] classes = generateClasses(db, n, students);
        Database.Classes classesTab = db.new Classes(classes);
        Database.AggregateGrades aggregateGradesTab = db.new AggregateGrades("33600");

        int w = 1000;
        int h = 600;

        Map<Character, Integer> g = aggregateGradesTab.getFreq();
        HistogramAlphaBet ha = new HistogramAlphaBet(g);

        HistogramAlphaBet.MyPieChart pi = ha.new MyPieChart(w, h);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, w, h);
        Canvas canvas = new Canvas(w, h);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        pi.draw(graphicsContext);

        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.setTitle("Assignment-4");
        stage.show();
    }
    public static void main(String[] arg) {
        launch();
    }

    /* For random input generation */
    public static String genStr(int n) {
        Random random = new Random();
        String str = "";
        for (int i=0; i<n; i++) {
            str += (char) (97+random.nextInt(26));
        }
        return str;
    }

    public static int genNum(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }

    public static char genGender() {
        Random random = new Random();
        int gen = random.nextInt(3);
        if(gen==0) return 'M';
        else if(gen==1) return 'F';
        else return 'U';
    }

    public static char genGrade() {
        Random random = new Random();
        int gen = random.nextInt(100);
        if(gen>90) return 'A';
        if(gen>65) return 'B';
        if(gen>41) return 'C';
        if(gen>19) return 'D';
        if(gen>9) return 'F';
        else return 'W';
    }

    public static String[][] generateStudents(int n){
        String[][] students = new String[n][5];
        for(int i=0; i<n; i++) {
            students[i][0] = String.valueOf(20000000+genNum(10000000));
            students[i][1] = genStr(10);
            students[i][2] = genStr(10);
            students[i][3] = students[i][1]+'.'+students[i][2]+"@citymail.cuny.edu";
            students[i][4] = String.valueOf(genGender());
        }
        return students;
    }

    public static String[][] generateClasses(Database db, int n, String[][] empids){
        String[][] classes = new String[3*n][6];
        String query = "select courseID, sectionNo from schedule;";
        String[][] course = new String[80][2];
        try {
            ResultSet rs = ClassScheduleInterface.executeQuery(db.getCon(),query);
            int idx = 0;
            while (rs.next()){
                course[idx][0] = rs.getString(1);
                course[idx][1] = rs.getString(2);
                idx++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<3; j++) {
                int ran = genNum(80);
                int ix = i*3+j;
                classes[ix][0] = course[ran][0];
                classes[ix][1] = empids[i][0];
                classes[ix][2] = course[ran][1];
                classes[ix][3] = "2022";
                classes[ix][4] = "Spring";
                classes[ix][5] = String.valueOf(genGrade());
            }
        }
        return classes;
    }

    public static String[][] genSpecificClass(Database db, int n, String[][] empids, String cid, int secNo) {
        String[][] classes = new String[n][6];
        for (int i=0; i<n; i++){
            classes[i][0] = cid;
            classes[i][1] = empids[genNum(n)][0];
            classes[i][2] = String.valueOf(secNo);
            classes[i][3] = "2022";
            classes[i][4] = "Spring";
            classes[i][5] = String.valueOf(genGrade());
        }
        return classes;
    }

}
