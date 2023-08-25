package com.example.assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Database implements ClassScheduleInterface{
    private Connection con;

    public Database() {
        this.con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.con =DriverManager.getConnection(host,userName,password);
            System.out.println("Connected to database Successfully");

        }
        catch(SQLException se){
            System.out.println(se.getMessage());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getCon() {
        return this.con;
    }

    public class Schedule{
        private String tab;
        public Schedule() {
            this.tab = "schedule";
            createSchedule();
            populateSchedule();

        }

        private void createSchedule(){
            // creating Schedule table
            String[] headers = {"courseID varchar(15)",
                                "sectionNo int",
                                "courseTitle varchar(50)",
                                "year int",
                                "semester varchar(10)",
                                "instructor varchar(50)",
                                "department varchar(50)",
                                "program varchar (50)",
                                "primary key(courseID, sectionNo)"};
            ClassScheduleInterface.createTable(con, tab, headers);
        }

        private void populateSchedule() {
            try {
                File scheduleFile = new File("scheduleSpring2021.txt");
                Scanner sc = new Scanner(scheduleFile);
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String values[] = line.split("\t");
                    values[0] = values[0].split(" ")[0];
                    ClassScheduleInterface.insertToTable(con, tab, values);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class Students{
        String tab;
        public Students(String[][] values){
            this.tab = "students";
            createStudents();
            populateStudents(values);
        }

        private void createStudents(){
            // creating students table
            String headers[] = {"emplID int primary key",
                                "firstName varchar(20)",
                                "lastName varchar(20)",
                                "email varchar(50)",
                                "gender char check(gender='F' or gender='M' or gender='U')"};
            ClassScheduleInterface.createTable(con, tab, headers);

        }

        private void populateStudents(String[][] values) {
            String[] myData = {"24005884", "Myesha", "Mahazabeen", "mmahaza000@citymail.cuny.edu", "F"};
            ClassScheduleInterface.insertToTable(con, tab, myData);
            for(String[] row : values){
                ClassScheduleInterface.insertToTable(con, tab, row);
            }
        }

        public void updateStudent(String sid, String fieldName, String value) {
            ClassScheduleInterface.updateTable(con, "students", "emplID", sid, fieldName, value);
        }
    }
    public class Courses{
        private String tab;
        public Courses() {
            tab = "courses";
            createCourses();
            populateCourses();
        }

        private void createCourses(){

            String headers[] = {"courseID varchar(15) primary key",
                                "courseTitle varchar(50)",
                                "department varchar(50)"};
            ClassScheduleInterface.createTable(con, tab, headers);
        }

        private void populateCourses() {
            try {
                String query = "select courseID, courseTitle, department from schedule";
                ResultSet res = ClassScheduleInterface.executeQuery(con, query);
                while (res.next()) {
                    String[] values = {res.getString(1), res.getString(2), res.getString(3)};
                    ClassScheduleInterface.insertToTable(con, "courses", values);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateCourses(String cid, String fieldName, String value) {
            ClassScheduleInterface.updateTable(con, "courses", "courseID", cid, fieldName, value);
        }

    }
    public class Classes{
        public Classes(String[][] values){
            createClasses();
            populateClasses(values);
        }

        private void createClasses(){
            String tab = "classes";
            // creating classes table
            String headers[] = {"courseID varchar(15)",
                                "studentID int",
                                "sectionNo int",
                                "year int",
                                "semester varchar(10)",
                                "grade char check(grade='A' or grade='B' or grade='C' or grade='D' or grade='E' or grade='F' or grade='W')",
                                "foreign key(studentID) references students(emplID)",
                                "foreign key(courseID) references courses(courseID)",
                                "primary key(courseID, studentID, sectionNo)"};
            ClassScheduleInterface.createTable(con, tab, headers);

        }

        private void populateClasses(String[][] values) {
            String[] myData = {"11300", "24005884", "41534", "2022", "Spring", "B"};
            ClassScheduleInterface.insertToTable(con, "classes", myData);
            for(String[] value : values) {
                ClassScheduleInterface.insertToTable(con, "classes", value);
            }
        }

        public void updateClasses(String keyField, String key, String fieldName, String value) {
            ClassScheduleInterface.updateTable(con, "classes", keyField, key, fieldName, value);
        }

    }
    public class AggregateGrades {
        String cid;
        public AggregateGrades(String cid) {
            this.cid = cid;
            createAggregateGrades();
            populateAggregateGrades();
        }

        private void createAggregateGrades() {
            String tab = "aggregateGrades";
            String[] headers = {"grade char primary key",
                                "numberStudents int"};
            ClassScheduleInterface.createTable(con, tab, headers);
        }

        private void populateAggregateGrades() {
            String query = "insert into aggregateGrades " +
                    "select grade, count(*) from classes " +
                    "where courseID = '" + this.cid +
                    "' group by grade;";
            ClassScheduleInterface.executeUpdate(con, query);
        }

        public Map<Character, Integer> getFreq() {
            Map<Character, Integer> freq = new HashMap<>();
            char[] validGrades = {'A', 'B', 'C', 'D', 'F', 'W'};
            for(char c:validGrades) {
                freq.put(c, 0);
            }
            String query = "select * from aggregateGrades;";
            try {
                ResultSet resultSet = ClassScheduleInterface.executeQuery(con, query);
                while (resultSet.next()) {
                    Character grade = resultSet.getString(1).charAt(0);
                    freq.put(grade, resultSet.getInt(2));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return freq;

        }
    }

}
