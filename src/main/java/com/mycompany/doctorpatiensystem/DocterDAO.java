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
import java.sql.SQLException;
import java.sql.Connection;
interface DocterInterface{
public abstract void add(String id,String name,String SO,String email,String contact,String address,String qual,String gender,String bld_grp,String joining);
public abstract int read(String userId);

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
   PreparedStatement stmt = connect.prepareCall(insert);
           stmt.setString(1, id);
           stmt.setString(2, name);
           stmt.setString(3, SO);
           stmt.setString(1, email);
           stmt.setString(2, contact);
           stmt.setString(3, address);
           stmt.setString(1, qual);
           stmt.setString(2, gender);
           stmt.setString(3, bld_grp);
           stmt.setString(3, joining);
           stmt.executeUpdate();
           connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


   
  


    public String[] read(String username) {
        String[] doc_data = new String[3];
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Connection Failed");
            return doc_data;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    details[0] = resultSet.getString("username");
                    details[1] = resultSet.getString("password");
                    details[2] = String.valueOf(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = Database.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return users;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");

                User user = new User(id, username, name);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public String[] read(int userId) {
        String[] details = new String[2];
        Connection conn = Database.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return details;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    details[0] = resultSet.getString("username");
                    details[1] = resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }
}