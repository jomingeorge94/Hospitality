package com.example.jomin.hospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends ActionBarActivity {

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
