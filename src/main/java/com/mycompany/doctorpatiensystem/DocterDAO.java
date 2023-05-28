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

class doctor {
    private int id;
    private String name;
    private String father_name;
    private String emailID;
    private int contact;
    private String address;
    private String qualification;
    private String gender;
    private String blood_group;
    private String joiningDate;

    public doctor(int id, String name, String father_name, String emailID, int contact, String address, String qualification, String gender, String blood_group, String joiningDate) {
        this.id = id;
        this.name = name;
        this.father_name = father_name;
        this.emailID = emailID;
        this.contact = contact;
        this.address = address;
        this.qualification = qualification;
        this.gender = gender;
        this.blood_group = blood_group;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
}

interface DoctorDaoInterface {
    void create(doctor doctor);
    doctor read(int doctorId);
    List<doctor> getAllDoctors();
    void update(int doctorId, doctor doctor);
    void delete(int doctorId);
}

public class DocterDAO implements DoctorDaoInterface {
    @Override
    public void create(doctor doc) {
        Connection connect = DBconnection.connect();
        if (connect == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "INSERT INTO doctors (id, name, father_name, emailID, contact, address, Qualification, gender, blood_group, joiningDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connect.prepareCall(query);
            stmt.setInt(1, doc.getId());
            stmt.setString(2, doc.getName());
            stmt.setString(3, doc.getFather_name());
            stmt.setString(4, doc.getEmailID());
            stmt.setInt(5, doc.getContact());
            stmt.setString(6, doc.getAddress());
            stmt.setString(7, doc.getQualification());
            stmt.setString(8, doc.getGender());
            stmt.setString(9, doc.getBlood_group());
            stmt.setString(10, doc.getJoiningDate());
            stmt.executeUpdate();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public doctor read(int doctorId) {
        doctor doc = null;
        Connection connect = DBconnection.connect();

        if (connect == null) {
            System.out.println("Couldn't establish connection");
            return null;
        }

        try (PreparedStatement statement = connect.prepareStatement("SELECT * FROM doctors WHERE id = ?")) {
            statement.setInt(1, doctorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String father_name = resultSet.getString("father_name");
                    String emailID = resultSet.getString("emailID");
                    int contact = resultSet.getInt("contact");
                    String address = resultSet.getString("address");
                    String qualification = resultSet.getString("Qualification");
                    String gender = resultSet.getString("gender");
                    String blood_group = resultSet.getString("blood_group");
                    String joiningDate = resultSet.getString("joiningDate");

                    doc = new doctor(id, name, father_name, emailID, contact, address, qualification, gender, blood_group, joiningDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doc;
    }

    @Override
    public List<doctor> getAllDoctors() {
        List<doctor> doctors = new ArrayList<>();
        Connection connect = DBconnection.connect();

        if (connect == null) {
            System.out.println("Couldn't establish connection");
            return doctors;
        }

        try (PreparedStatement statement = connect.prepareStatement("SELECT * FROM doctors");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String father_name = resultSet.getString("father_name");
                String emailID = resultSet.getString("emailID");
                int contact = resultSet.getInt("contact");
                String address = resultSet.getString("address");
                String qualification = resultSet.getString("Qualification");
                String gender = resultSet.getString("gender");
                String blood_group = resultSet.getString("blood_group");
                String joiningDate = resultSet.getString("joiningDate");

                doctor doc = new doctor(id, name, father_name, emailID, contact, address, qualification, gender, blood_group, joiningDate);
                doctors.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    @Override
    public void update(int doctorId, doctor doctor) {
        Connection connect = DBconnection.connect();
        if (connect == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "UPDATE doctors SET name = ?, father_name = ?, emailID = ?, contact = ?, address = ?, Qualification = ?, gender = ?, blood_group = ?, joiningDate = ? WHERE id = ?";
            PreparedStatement stmt = connect.prepareCall(query);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getFather_name());
            stmt.setString(3, doctor.getEmailID());
            stmt.setInt(4, doctor.getContact());
            stmt.setString(5, doctor.getAddress());
            stmt.setString(6, doctor.getQualification());
            stmt.setString(7, doctor.getGender());
            stmt.setString(8, doctor.getBlood_group());
            stmt.setString(9, doctor.getJoiningDate());
            stmt.setInt(10, doctorId);
            stmt.executeUpdate();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int doctorId) {
        Connection connect = DBconnection.connect();
        if (connect == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "DELETE FROM doctors WHERE id = ?";
            PreparedStatement stmt = connect.prepareCall(query);
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
