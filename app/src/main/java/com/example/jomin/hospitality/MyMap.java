package com.example.jomin.hospitality;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Jomin on 08/04/2016.
 */
public class MyMap extends ActionBarActivity {


    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        String s = getIntent().getStringExtra("SERVICE");

        Bundle bundle = new Bundle();
        bundle.putString("SERVICE", s);

        Fragment l = new LocationFragment();
        l.setArguments(bundle);

        // initialising the object of the FragmentManager. Here I'm passing getSupportFragmentManager(). You can pass getFragmentManager() if you are coding for Android 3.0 or above.
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.lay, l)
                .commit();

    }
}


