package com.example.jomin.hospitality;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class Profile extends ActionBarActivity {

    TextView logOut;
    UserLocalDrive userLocalDrive;
    LinearLayout goBack;
    User u;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        final SharedPreferences sp = getSharedPreferences("GLOB", MODE_PRIVATE);

        ServerRequests serverRequest = new ServerRequests(Profile.this);

        String[] arraySpinner;


        arraySpinner = new String[] {
                "Male", "Female", "Other"
        };

        final Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        final TextView t = (TextView) findViewById(R.id.contact);




        serverRequest.getUserProfile(new GetUserCallback() {
            @Override
            public void done(final User returnedUser) {

                ArrayAdapter arr = (ArrayAdapter) s.getAdapter();
                int i = arr.getPosition(returnedUser.gender);
                s.setSelection(i);

                t.setText(returnedUser.contact);

                u = returnedUser;


            }
        }, sp.getString("EMAIL", "not_found"));




        TextView done = (TextView) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.gender = s.getSelectedItem().toString();
                u.contact = t.getText().toString();
                ServerRequests serverRequest = new ServerRequests(Profile.this);
                serverRequest.postUserDataInBackground(u, new GetUserCallback() {
                    @Override
                    public void done(User returnedUser) {
                        Log.i("UPDATE", "UPDA");
                    }

                });
            }
        });


        goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Profile.this ,MainActivity.class);
                Profile.this.startActivity(intentMain);
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
        return;
    }


}
