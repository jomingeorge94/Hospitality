package com.example.jomin.hospitality;

/**
 * Created by Jomin on 06/02/2016.
 */
public class User {

    String fullname, emailaddress, password, gender, contact;

    public User(String name,String email, String password) {
        this.fullname = name;
        this.emailaddress = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.emailaddress = email;
        this.password = password;
        this.fullname = "";
    }

    public User(String name,String email, String password, String gender) {
        this.fullname = name;
        this.emailaddress = email;
        this.password = password;
        this.gender = gender;
    }

    public User(String name,String email, String password, String gender, String contact) {
        this.fullname = name;
        this.emailaddress = email;
        this.password = password;
        this.gender = gender;
        this.contact = contact;
    }
}
