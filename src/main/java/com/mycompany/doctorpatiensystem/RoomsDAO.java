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

class Room {
    private int room_no;
    private String type;
    private int charges;

    public Room(int room_no, String type, int charges) {
        this.room_no = room_no;
        this.type = type;
        this.charges = charges;
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
}

interface RoomDaoInterface {
    void create(Room room);
    Room read(int roomNo);
    List<Room> getAllRooms();
    void update(int roomNo, Room room);
    void delete(int roomNo);
}

public class RoomsDAO implements RoomDaoInterface {
    @Override
    public void create(Room room) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection Failed");
            return;
        }
        try {
            String query = "INSERT INTO rooms (room_no, type, charges) VALUES (?, ?, ?)";
            PreparedStatement insert = conn.prepareCall(query);
            insert.setInt(1, room.getRoom_no());
            insert.setString(2, room.getType());
            insert.setInt(3, room.getCharges());
            insert.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Room read(int roomNo) {
        Room room = null;
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Connection failed");
            return null;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM rooms WHERE room_no = ?")) {
            statement.setInt(1, roomNo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int room_no = resultSet.getInt("room_no");
                    String type = resultSet.getString("type");
                    int charges = resultSet.getInt("charges");

                    room = new Room(room_no, type, charges);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        Connection conn = DBconnection.connect();

        if (conn == null) {
            System.out.println("Connection failed");
            return rooms;
        }

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM rooms");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int room_no = resultSet.getInt("room_no");
                String type = resultSet.getString("type");
                int charges = resultSet.getInt("charges");

                Room room = new Room(room_no, type, charges);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public void update(int roomNo, Room room) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection failed");
            return;
        }
        try {
            String query = "UPDATE rooms SET type = ?, charges = ? WHERE room_no = ?";
            PreparedStatement update = conn.prepareCall(query);
            update.setString(1, room.getType());
            update.setInt(2, room.getCharges());
            update.setInt(3, roomNo);
            update.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int roomNo) {
        Connection conn = DBconnection.connect();
        if (conn == null) {
            System.out.println("Connection failed");
            return;
        }
        try {
            String query = "DELETE FROM rooms WHERE room_no = ?";
            PreparedStatement del = conn.prepareCall(query);
            del.setInt(1, roomNo);
            del.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
