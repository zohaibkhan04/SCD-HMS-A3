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

class PatientAdmission {
    private int patientID;
    private String name;
    private int docID;
    private String docName;
    private String disease;
    private String gender;
    private String remarks;
    private String bloodGroup;
    private String admissionDate;
    private int roomNo;

    public PatientAdmission(int patientID, String name, int docID, String docName, String disease,
                            String gender, String remarks, String bloodGroup, String admissionDate, int roomNo) {
        this.patientID = patientID;
        this.name = name;
        this.docID = docID;
        this.docName = docName;
        this.disease = disease;
        this.gender = gender;
        this.remarks = remarks;
        this.bloodGroup = bloodGroup;
        this.admissionDate = admissionDate;
        this.roomNo = roomNo;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
}

interface PatientAdmissionDaoInterface {
    void create(PatientAdmission patientAdmission);
    PatientAdmission read(int patientID);
    List<PatientAdmission> getAllPatientAdmissions();
    void update(int patientID, PatientAdmission patientAdmission);
    void delete(int patientID);
}

public class PatientAdmissionDAO implements PatientAdmissionDaoInterface {
    @Override
    public void create(PatientAdmission patientAdmission) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection Failed");
            return;
        }
        try {
            String query = "INSERT INTO patient_admission (patientID, name, docID, docName, disease, gender, " +
                    "remarks, bloodGroup, admissionDate, roomNo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insert = conn.prepareCall(query);
            insert.setInt(1, patientAdmission.getPatientID());
            insert.setString(2, patientAdmission.getName());
            insert.setInt(3, patientAdmission.getDocID());
            insert.setString(4, patientAdmission.getDocName());
            insert.setString(5, patientAdmission.getDisease());
            insert.setString(6, patientAdmission.getGender());
            insert.setString(7, patientAdmission.getRemarks());
            insert.setString(8, patientAdmission.getBloodGroup());
            insert.setString(9, patientAdmission.getAdmissionDate());
            insert.setInt(10, patientAdmission.getRoomNo());
            insert.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PatientAdmission read(int patientID) {
        PatientAdmission patientAdmission = null;
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Connection failed");
            return null;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM patient_admission WHERE patientID = ?")) {
            statement.setInt(1, patientID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int patientIDValue = resultSet.getInt("patientID");
                    String name = resultSet.getString("name");
                    int docID = resultSet.getInt("docID");
                    String docName = resultSet.getString("docName");
                    String disease = resultSet.getString("disease");
                    String gender = resultSet.getString("gender");
                    String remarks = resultSet.getString("remarks");
                    String bloodGroup = resultSet.getString("bloodGroup");
                    String admissionDate = resultSet.getString("admissionDate");
                    int roomNo = resultSet.getInt("roomNo");

                    patientAdmission = new PatientAdmission(patientIDValue, name, docID, docName, disease,
                            gender, remarks, bloodGroup, admissionDate, roomNo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientAdmission;
    }

    @Override
    public List<PatientAdmission> getAllPatientAdmissions() {
        List<PatientAdmission> patientAdmissions = new ArrayList<>();
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Connection failed");
            return patientAdmissions;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM patient_admission");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int patientID = resultSet.getInt("patientID");
                String name = resultSet.getString("name");
                int docID = resultSet.getInt("docID");
                String docName = resultSet.getString("docName");
                String disease = resultSet.getString("disease");
                String gender = resultSet.getString("gender");
                String remarks = resultSet.getString("remarks");
                String bloodGroup = resultSet.getString("bloodGroup");
                String admissionDate = resultSet.getString("admissionDate");
                int roomNo = resultSet.getInt("roomNo");

                PatientAdmission patientAdmission = new PatientAdmission(patientID, name, docID, docName, disease,
                        gender, remarks, bloodGroup, admissionDate, roomNo);
                patientAdmissions.add(patientAdmission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientAdmissions;
    }

    @Override
    public void update(int patientID, PatientAdmission patientAdmission) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection failed");
            return;
        }
        try {
            String query = "UPDATE patient_admission SET name = ?, docID = ?, docName = ?, disease = ?, gender = ?, " +
                    "remarks = ?, bloodGroup = ?, admissionDate = ?, roomNo = ? WHERE patientID = ?";
            PreparedStatement update = conn.prepareCall(query);
            update.setString(1, patientAdmission.getName());
            update.setInt(2, patientAdmission.getDocID());
            update.setString(3, patientAdmission.getDocName());
            update.setString(4, patientAdmission.getDisease());
            update.setString(5, patientAdmission.getGender());
            update.setString(6, patientAdmission.getRemarks());
            update.setString(7, patientAdmission.getBloodGroup());
            update.setString(8, patientAdmission.getAdmissionDate());
            update.setInt(9, patientAdmission.getRoomNo());
            update.setInt(10, patientID);
            update.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int patientID) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection failed");
            return;
        }
        try {
            String query = "DELETE FROM patient_admission WHERE patientID = ?";
            PreparedStatement del = conn.prepareCall(query);
            del.setInt(1, patientID);
            del.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

