package com.example.jomin.hospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FindServices extends ActionBarActivity {

    UserLocalDrive userLocalDrive;
    ImageView profile;
    LinearLayout goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_services);

        ImageView hospital = (ImageView)findViewById(R.id.hospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "hospital");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView pharmacy = (ImageView)findViewById(R.id.pharmacy);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "pharmacy");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView clinic = (ImageView)findViewById(R.id.clinics);
        clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "health");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView fracture = (ImageView)findViewById(R.id.fracture);
        fracture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "health");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView gp = (ImageView)findViewById(R.id.gp);
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "doctor");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView dentist = (ImageView)findViewById(R.id.dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "dentist");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView eyecare = (ImageView)findViewById(R.id.eyecare);
        eyecare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "hospital");
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView sexualhealth = (ImageView)findViewById(R.id.sexualhealth);
        sexualhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "health");
                FindServices.this.startActivity(intentMain);
            }
        });

        goBack = (LinearLayout)findViewById(R.id.back_button);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this ,MainActivity.class);
                FindServices.this.startActivity(intentMain);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // return;
    }
}