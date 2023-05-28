/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctorpatiensystem;

/**
 *
 * @author Zohaib khan
 */
import java.awt.List;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Connection;
interface DocterInterface
{
public abstract void add(String id,String name,String SO,String email,String contact,String address,String qual,String gender,String bld_grp,String joining);
public abstract int read(String userId);
public abstract List<docter> getAllUsers();
}

public class DocterDAO {
    public void add(String id,String name,String SO,String email,String contact,String address,String qual,String gender,String bld_grp,String joining)
    {
        Connection connect=DBconnection.connect();
        if(connect==null)
        {
            System.out.println("Connection Failed");
            return;
        }
        try{
        String insert="INSERT INTO docters(id, name, father_name, emailID, contact, address, Qualification, gender, blood_group, joiningDate) VALUES(?,?,?,?,?,?,?,?,?)";
   PreparedStatement query = connect.prepareCall(insert);
           query.setString(1, id);
           query.setString(2, name);
           query.setString(3, SO);
           query.setString(1, email);
           query.setString(2, contact);
           query.setString(3, address);
           query.setString(1, qual);
           query.setString(2, gender);
           query.setString(3, bld_grp);
           query.setString(3, joining);
           query.executeUpdate();
           connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


   
  


    public String[] read(String userid) {
        String[] doc_data = new String[3];
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Connection Failed");
            return doc_data;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM docters WHERE id = ?")) {
            statement.setString(1, userid);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    doc_data[0] = resultSet.getString("username");
                    doc_data[1] = resultSet.getString("password");
                    doc_data[2] = String.valueOf(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doc_data;
    }

    /**
     *
     * @return
     */
    
    public List<docter> getAlldocters() {
        List<docter> docter = new ArrayList<>();
        Connection connect = DBconnection.connect();

        if (connect == null) {
            System.out.println("Connection Failed");
            return docter;
        }

        try (PreparedStatement statement = connect.prepareStatement("SELECT * FROM docters");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
               
                String name = resultSet.getString("name");
                String SO = resultSet.getString("father_name");
                String email = resultSet.getString("emailID");
                int contact = resultSet.getInt("contact");
                String address = resultSet.getString("address");
                String qual = resultSet.getString("Qualification");
                String gender = resultSet.getString("gender");
                String bld_grp = resultSet.getString("blood_group");
                String joining = resultSet.getString("joiningDate");

                docter doc = new docter(id, name, SO, email, contact, address, qual, gender, bld_grp, joining);
                doc.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    
    
}