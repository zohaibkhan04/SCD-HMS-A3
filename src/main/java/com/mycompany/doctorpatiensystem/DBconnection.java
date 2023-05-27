/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctorpatiensystem;

/**
 *
 * @author Zohaib khan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {
    private static final String hostname = "localhost";
    private static final int port = 3306;
    private static final String database = "HMS";
    private static final String username = "root";
    private static final String password = "";

    public static Connection connect() {
        try {
            String uri = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database);
            return DriverManager.getConnection(uri, username, password);
        } catch (SQLException e) {
           System.out.format("An error occurred: %s\n", e.getMessage());
        }

        return null;
    }

    public static void initialize() {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection Failed");
            return;
        }

        try {
            Statement statement = conn.createStatement();
            String createdocterTable = "CREATE TABLE IF NOT EXISTS docters ("
                    + "id INT  PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "father_name VARCHAR(255) NOT NULL,"
                    + "emailID VARCHAR(255) NOT NULL,"
                    + "contact INT NOT NULL,"
                    + "address VARCHAR(255) NOT NULL,"
                    + "Qualification VARCHAR(255) NOT NULL,"
                    + "gender VARCHAR(255) NOT NULL,"
                    + "blood_group VARCHAR(255) NOT NULL,"
                    +"joiningDate VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createdocterTable);

            
            String createPatientTable = "CREATE TABLE IF NOT EXISTS patients ("
                    + "patient_id INT  PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "father_name VARCHAR(255) NOT NULL,"
                    + "emailID VARCHAR(255) NOT NULL,"
                    + "contact INT NOT NULL,"
                    + "address VARCHAR(255) NOT NULL,"
                    + "gender VARCHAR(255) NOT NULL,"
                    + "blood_group VARCHAR(255) NOT NULL,"
                    +"age VARCHAR(255) NOT NULL,"
                    + "info VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createPatientTable);
            
            String createRoomTable = "CREATE TABLE IF NOT EXISTS rooms ("
                    + "room_no INT  PRIMARY KEY,"
                    + "type VARCHAR(255) NOT NULL,"
                    + "charges INT NOT NULL"
                    + ")";
            statement.executeUpdate(createRoomTable);
            String createServicesTable = "CREATE TABLE IF NOT EXISTS services ("
                    + "name VARCHAR(255) NOT NULL,"
                    + "date VARCHAR(255) NOT NULL,"
                    + "charges INT NOT NULL,"
                    + "CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patients (patient_id)"
                    + ")";
            statement.executeUpdate(createServicesTable);
            conn.close();
        }
        catch (SQLException e) {
            System.out.format("ERROR: %s", e.getMessage());
        }
    }
}