/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctorpatiensystem;

/**
 *
 * @author Zohaib khan
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


interface PatientDaoInterface {
    void create(patients patient);
    int find(int patientId);
    patients read(int patientId);
    List<patients> getAllPatients();
    void update(int patientId, patients patient);
    void delete(int patientId);
}

public class PatientDAO implements PatientDaoInterface {
    @Override
    public void create(patients patient) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "INSERT INTO patients (patient_id, name, father_name, emailID, contact, address, gender, blood_group, age, info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insert = conn.prepareCall(query);
            insert.setInt(1, patient.getPatient_id());
            insert.setString(2, patient.getName());
            insert.setString(3, patient.getFather_name());
            insert.setString(4, patient.getEmailID());
            insert.setInt(5, patient.getContact());
            insert.setString(6, patient.getAddress());
            insert.setString(7, patient.getGender());
            insert.setString(8, patient.getBlood_group());
            insert.setString(9, patient.getAge());
            insert.setString(10, patient.getInfo());
            insert.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int find(int patientId) {
        int count = 0;
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return -1;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) as count FROM patients WHERE patient_id = ?")) {
            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public patients read(int patientId) {
        patients patient = null;
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return null;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM patients WHERE patient_id = ?")) {
            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("patient_id");
                    String name = resultSet.getString("name");
                    String father_name = resultSet.getString("father_name");
                    String emailID = resultSet.getString("emailID");
                    int contact = resultSet.getInt("contact");
                    String address = resultSet.getString("address");
                    String gender = resultSet.getString("gender");
                    String blood_group = resultSet.getString("blood_group");
                    String age = resultSet.getString("age");
                    String info = resultSet.getString("info");

                    patient = new patients(id, name, father_name, emailID, contact, address, gender, blood_group, age, info);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    @Override
    public List<patients> getAllPatients() {
        List<patients> patients = new ArrayList<>();
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return patients;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM patients");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String name = resultSet.getString("name");
                String father_name = resultSet.getString("father_name");
                String emailID = resultSet.getString("emailID");
                int contact = resultSet.getInt("contact");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");
                String blood_group = resultSet.getString("blood_group");
                String age = resultSet.getString("age");
                String info = resultSet.getString("info");

                patients patient = new patients(id, name, father_name, emailID, contact, address, gender, blood_group, age, info);
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    public void update(int patientId, patients patient) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "UPDATE patients SET name = ?, father_name = ?, emailID = ?, contact = ?, address = ?, gender = ?, blood_group = ?, age = ?, info = ? WHERE patient_id = ?";
            PreparedStatement insert = conn.prepareCall(query);
            insert.setString(1, patient.getName());
            insert.setString(2, patient.getFather_name());
            insert.setString(3, patient.getEmailID());
            insert.setInt(4, patient.getContact());
            insert.setString(5, patient.getAddress());
            insert.setString(6, patient.getGender());
            insert.setString(7, patient.getBlood_group());
            insert.setString(8, patient.getAge());
            insert.setString(9, patient.getInfo());
            insert.setInt(10, patientId);
            insert.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int patientId) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "DELETE FROM patients WHERE patient_id = ?";
            PreparedStatement insert = conn.prepareCall(query);
            insert.setInt(1, patientId);
            insert.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

