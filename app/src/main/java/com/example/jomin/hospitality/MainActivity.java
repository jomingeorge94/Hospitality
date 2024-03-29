package com.example.jomin.hospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends FragmentActivity {

    TextView logOut;
    String email;
    UserLocalDrive userLocalDrive;
    ImageView profile, service, remainder, appointments, callHelp, usefulLinks, sendFeedback, aboutApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        logOut = (TextView)findViewById(R.id.main_menu_logout);
        userLocalDrive = new UserLocalDrive(this);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(" Quit? ")
                        .setContentText("Do you really want to quit ?")
                        .setCancelText("No")
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                userLocalDrive.clearUserData();
                                userLocalDrive.setUserLoggedIn(false);


                                Intent intentMain = new Intent(MainActivity.this ,Login.class);
                                MainActivity.this.startActivity(intentMain);
                            }
                        })
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();

            }
        });


        profile = (ImageView)findViewById(R.id.user_profile);



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,Profile.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView find = (ImageView)findViewById(R.id.find_service);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,FindServices.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView callforhelp = (ImageView)findViewById(R.id.callforhelp);

        callforhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,CallForHelp.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView reminder = (ImageView)findViewById(R.id.reminder);

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,Reminder.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView appointment = (ImageView)findViewById(R.id.appointment);

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,Appointments.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView aboutus = (ImageView)findViewById(R.id.about_us);

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,AboutUs.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView usefullinks = (ImageView)findViewById(R.id.useful_links);

        usefullinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,UsefulLinks.class);
                MainActivity.this.startActivity(intentMain);
            }
        });

        final ImageView sendfeedback = (ImageView)findViewById(R.id.sendFeedback);

        sendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MainActivity.this ,Feedback.class);
                MainActivity.this.startActivity(intentMain);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (userAuthenticated() == true){
            displayAuthenticatedDetails();
        }else {
            Intent intentMain = new Intent(MainActivity.this ,Login.class);
            MainActivity.this.startActivity(intentMain);
        }

    }

    private void displayAuthenticatedDetails() {

    }


    private boolean userAuthenticated() {
        return userLocalDrive.getUserLoggedIn();
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
        return;
    }
}
