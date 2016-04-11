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
                intentMain.putExtra("TEXT", "Hospital provides medical and surgical treatment and nursing care for sick or injured people. " +
                        "Hospital services fall under secondary care and with the exception of emergency care you'll need a referral to access treatment. ");
                intentMain.putExtra("IMAGE", R.drawable.hospital_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView pharmacy = (ImageView)findViewById(R.id.pharmacy);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "pharmacy");
                intentMain.putExtra("TEXT", "Pharmacists are responsible for the quality of medicines supplied to patients and " +
                        "also supervise the medicines supply chain and ensure pharmacy premises and systems are fit for purpose. " +
                        "A person calling themselves a pharmacist must be registered with the GPhC.");
                intentMain.putExtra("IMAGE", R.drawable.pharmacy_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView clinic = (ImageView)findViewById(R.id.clinics);
        clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "health");
                intentMain.putExtra("TEXT", "NHS walk-in centres (WICs) offer convenient access to a range of treatments. WICs are managed by " +
                        "Clinical Commissioning Groups (CCGs), dealing with minor illnesses and injuries. NHS WICs are usually managed by a nurse and are available to everyone. " +
                        "Patients do not need an appointment. Most centres are open 365 days a year and outside office hours.");
                intentMain.putExtra("IMAGE", R.drawable.walk_in_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView fracture = (ImageView)findViewById(R.id.fracture);
        fracture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "health");
                intentMain.putExtra("TEXT", "Fracture Clinic deals with patients who have been diagnosed with a fracture in A&E or have been sent by their GP with a suspected fracture. " +
                        "The Fracture Clinic is not a drop in centre, but is run on an appointment system only. " +
                        "Please telephone for an appointment and always bring your appointment card with you.");
                intentMain.putExtra("IMAGE", R.drawable.fracture_clinic_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView gp = (ImageView)findViewById(R.id.gp);
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "doctor");
                intentMain.putExtra("TEXT", "GPs deal with a whole range of health problems. They also provide health education, " +
                        "offer advice on smoking and diet, run clinics, give vaccinations and carry out simple surgical operations. If your GP cannot deal with a problem, then you’ll usually be referred to a hospital for tests, treatment, " +
                        "or to see a consultant with specialist knowledge.");
                intentMain.putExtra("IMAGE", R.drawable.gp_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView dentist = (ImageView)findViewById(R.id.dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "dentist");
                intentMain.putExtra("TEXT", "Everyone should be able to access good-quality NHS dental services. There is no need to register with a dentist in the same way as with a " +
                        "GP because you are not bound to a catchment area. " +
                        "Simply find a dental practice that's convenient for you, whether it's near your home or work, and phone them to see if any appointments are available. ");
                intentMain.putExtra("IMAGE", R.drawable.dentist_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView eyecare = (ImageView)findViewById(R.id.eyecare);
        eyecare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "hospital");
                intentMain.putExtra("TEXT", "NHS optician is still the term most of us associate with their eye healthcare professional. " +
                        "However, the term is being used less and less within the profession and it is important you know who you are dealing with when you have your eyecare appointment. " +
                        "When you visit an optician, you'll have your sight tested by an ophthalmic practitioner, which can mean either an optometrist or an ophthalmic  medical practitioner.");
                intentMain.putExtra("IMAGE", R.drawable.eye_care_cover);
                FindServices.this.startActivity(intentMain);
            }
        });

        ImageView sexualhealth = (ImageView)findViewById(R.id.sexualhealth);
        sexualhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(FindServices.this , MyMap.class);
                intentMain.putExtra("SERVICE", "health");
                intentMain.putExtra("TEXT", "Sexual health services are free and available to everyone regardless of sex, age, ethnic origin and sexual orientation." +
                        "If you have a disability and you have special requirements, or if English is not your first language, you should make arrangements in advance." +
                        "If you are unable to get to your GP or to a clinic, it may be possible for someone to visit you at home.");
                intentMain.putExtra("IMAGE", R.drawable.sexual_cover);
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