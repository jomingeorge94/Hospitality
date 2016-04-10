package com.example.jomin.hospitality;

/**
 * Created by Jomin on 10/04/2016.
 */
public class MapsRepresenter {

    public double lat;
    public double lng;
    public String name;
    public String addr;

    public MapsRepresenter(){


    }

    @Override
    public String toString(){
        return "Lat: " + lat + " LNG: " + lng + " NAME" + name + " addr" + addr;
    }
}
