package com.example.jomin.hospitality;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jomin Kaitholil George on 06/02/2016.
 */
public class UserLocalDrive {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalDrive(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("name", user.fullname);
        userLocalDatabaseEditor.putString("username", user.emailaddress);
        userLocalDatabaseEditor.putString("password", user.password);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String name = userLocalDatabase.getString("name", "");
        String emailaddress = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User user = new User(name, emailaddress, password);
        return user;
    }

    public boolean getUserLoggedIn () {
        if (userLocalDatabase.getBoolean("loggedIn", false) == true){
            return true;
        }else {
            return false;
        }
    }
}