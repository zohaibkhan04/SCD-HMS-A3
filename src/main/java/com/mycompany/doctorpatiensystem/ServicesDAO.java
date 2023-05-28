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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
class Services {
    private String name;
    private String date;
    private int charges;

    public Services(String name, String date, int charges) {
        this.name = name;
        this.date = date;
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
}




interface ServicesDaoInterface {
    void create(Services service);
    Services read(String serviceName);
    List<Services> getAllServices();
    void update(String serviceName, Services service);
    void delete(String serviceName);
}

public class ServicesDAO implements ServicesDaoInterface {
    @Override
    public void create(Services service) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "INSERT INTO services (name, date, charges) VALUES (?, ?, ?)";
            PreparedStatement insert = conn.prepareStatement(query);
            insert.setString(1, service.getName());
            insert.setString(2, service.getDate());
            insert.setInt(3, service.getCharges());
            insert.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Services read(String serviceName) {
        Services service = null;
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return null;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM services WHERE name = ?")) {
            statement.setString(1, serviceName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String date = resultSet.getString("date");
                    int charges = resultSet.getInt("charges");

                    service = new Services(name, date, charges);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    @Override
    public List<Services> getAllServices() {
        List<Services> services = new ArrayList<>();
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return services;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM services");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                int charges = resultSet.getInt("charges");

                Services service = new Services(name, date, charges);
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public void update(String serviceName, Services service) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "UPDATE services SET name = ?, date = ?, charges = ? WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, service.getName());
            stmt.setString(2, service.getDate());
            stmt.setInt(3, service.getCharges());
            stmt.setString(4, serviceName);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String serviceName) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Couldn't establish connection");
            return;
        }
        try {
            String query = "DELETE FROM services WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, serviceName);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
