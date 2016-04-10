package com.example.jomin.hospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;

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

        LinearLayout goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MyMap.this ,FindServices.class);
                MyMap.this.startActivity(intentMain);
            }
        });

    }

    public void onBackPressed() {
        return;
    }

}


