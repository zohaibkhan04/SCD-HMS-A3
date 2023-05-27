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
public abstract void add(String id,String name,String SO,String email,int contact,String address,String qual,String gender,String bld_grp,String joining);
public abstract int read(String userId);

}

public class DocterDAO {
    public void add(String id,String name,String SO,String email,int contact,String address,String qual,String gender,String bld_grp,String joining)
    {
        
    }    
}
