package com.example.hotelmanagementsystem.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
//    public static void main(String[] args) {
//        getConnections();
//    }
    public static Connection getConnections(){
        //jdbc:mariadb://localhost:3306
        try {
            String dbURL = "jdbc:sqlserver://GALAXY:1433;encrypt=false;databaseName=Hotel_Management_System;user=sa;password=123";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                 System.out.println("Connected");
                return conn;
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);

        }
        return null;
    }

    public static void closeConnections(){

    }
}