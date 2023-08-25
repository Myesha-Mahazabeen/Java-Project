package com.example.assignment4;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Database db = new Database();
        Database.Schedule scheduleTab = db.new Schedule();
        String q = "set foreign_key_checks=0;";
        ClassScheduleInterface.executeUpdate(db.getCon(), q);
        String[][] students = Main.generateStudents(50);
        Database.Students studentsTab = db.new Students(students);
        Database.Courses coursesTab = db.new Courses();
        String[][] classes = Main.generateClasses(db, 50, students);
        Database.Classes classesTab = db.new Classes(classes);
        Database.AggregateGrades aggregateGradesTab = db.new AggregateGrades("33600");

        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.println("press anything to start updating");
        String a = sc.next();

        studentsTab.updateStudent("24005884", "firstname", "Hey");
        coursesTab.updateCourses("30100", "courseTitle", "good course");
        classesTab.updateClasses("sectionNo", "34143", "semester", "Summer");
    }
}
