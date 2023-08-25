package com.example.assignment4;
import java.sql.*;

public interface ClassScheduleInterface {
     String host="jdbc:mysql://localhost:3306/assignment4",
             userName= "root",
             password="12345678";

     static void createTable(Connection con, String tabName, String[] headers){
          String validateTableQ="Drop table if exists "+tabName+";";
          executeUpdate(con, validateTableQ);
          String createTableQ= "Create table "+tabName+" (";
          for (int i=0; i<headers.length; i++) {
               createTableQ+=headers[i];
               if(i != headers.length-1)
                    createTableQ+= ", ";
               else
                    createTableQ+= ");";
          }
          executeUpdate(con, createTableQ);

     }
     public static void insertToTable(Connection con,String tabName, String[] values){
          String insertValue = "insert into "+tabName+" values (";
          PreparedStatement ps;

          String q = insertValue;
          for (int i = 0; i < values.length; i++) {
               q += "'" + values[i] + "'";
               if (i != values.length-1) q += ",";
               else q += ");";
          }
          executeUpdate(con, q);
     }

     public static void updateTable(Connection con, String table, String keyField, String key, String field, String replacement) {
          String query = "update "+table+" set "+field+"='"+replacement+"'" +
                         "where "+keyField+"='"+key+"';";
          executeUpdate(con, query);
     }

     public static ResultSet executeQuery(Connection con, String query) {
          System.out.println(query);
          try {
               PreparedStatement ps = con.prepareStatement(query);
               ResultSet rs = ps.executeQuery();
               return rs;
          } catch (SQLException e) {
//               e.printStackTrace();
          }
          return null;
     }
     public static void executeUpdate(Connection con, String up) {
          System.out.println(up);
          try {
               PreparedStatement ps = con.prepareStatement(up);
               ps.executeUpdate();
          } catch (SQLException e) {
//               e.printStackTrace();
          }
     }
}
