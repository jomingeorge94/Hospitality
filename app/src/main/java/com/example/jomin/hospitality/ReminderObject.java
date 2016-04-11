package com.example.jomin.hospitality;

/**
 * Created by Jomin on 06/02/2016.
 */
public class ReminderObject {

    String type, date, time;
    int id;

    public ReminderObject(String type) {
        this.type = type;
    }

    public ReminderObject(String type, String date, String time, int id)
    {
        this.date = date;
        this.time = time;
        this.type = type;
        this.id = id;
    }

    public ReminderObject(String type, String date, String time)
    {
        this.date = date;
        this.type = type;
        this.time = time;
    }



    public String toString(){
        return "Type: " + type + "  DATE: " + date + " TIME: " + time;
    }

}
