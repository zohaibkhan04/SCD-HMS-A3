/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctorpatiensystem;

/**
 *
 * @author Zohaib khan
 */
public class docter {
     private int id;
     private String name;
     private String SO;
     private String email;
     private int contact;
     private String address;
     private String qual;
     private String gender;
     private String bld_grp;
     private String joining;
 public docter(int id, String name, String SO, String email, int contact, String address, String qual, String gender, String bld_grp, String joining) {
        this.id = id;
        this.name = name;
        this.SO = SO;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.qual = qual;
        this.gender = gender;
        this.bld_grp = bld_grp;
        this.joining = joining;
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

    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBld_grp() {
        return bld_grp;
    }

    public void setBld_grp(String bld_grp) {
        this.bld_grp = bld_grp;
    }

    public String getJoining() {
        return joining;
    }

    public void setJoining(String joining) {
        this.joining = joining;
    }
     
}
