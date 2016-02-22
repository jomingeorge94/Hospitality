package com.example.jomin.hospitality;

/**
 * Created by Jomin on 06/02/2016.
 */
public class User {

    String fullname, emailaddress, password;

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
}
