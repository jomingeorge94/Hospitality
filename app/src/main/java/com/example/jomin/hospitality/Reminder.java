package com.example.jomin.hospitality;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Reminder extends ActionBarActivity {

    TextView logOut;
    UserLocalDrive userLocalDrive;
    LinearLayout goBack;
    User u;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reminders);

        final SharedPreferences sp = getSharedPreferences("GLOB", MODE_PRIVATE);

        String[] arraySpinner;

        arraySpinner = new String[] {
                "Prescription", "Appointments", "Dentist", "Surgery"
        };

        final Spinner s = (Spinner) findViewById(R.id.types);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        final EditText date = (EditText) findViewById(R.id.reminder_date);
        final EditText time = (EditText) findViewById(R.id.reminder_time);


        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = date.getText().toString();
                if(text.length() == 2 || text.length() == 5){
                    date.append("/");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        time.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = time.getText().toString();
                if(text.length() == 2){
                    time.append(":");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        TextView done = (TextView) findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date.getText().toString().matches("") || time.getText().toString().matches("")  || s.getSelectedItem() ==null){

                    new SweetAlertDialog(Reminder.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Something went wrong...")
                            .setContentText("You need to enter all fields !!")
                            .show();
                } else {
                    //date regex has been used from http://stackoverflow.com/questions/15491894/regex-to-validate-date-format-dd-mm-yyyy
                    // time regex has been used from http://stackoverflow.com/questions/8699636/regex-validation-for-time-entries
                    if(date.getText().toString().matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")
                            || time.getText().toString().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                        ServerRequests serverRequest = new ServerRequests(Reminder.this);
                        serverRequest.postReminderDataInBackground(new ReminderObject(s.getSelectedItem().toString(), date.getText().toString(), time.getText().toString()), new GetUserCallback() {
                            @Override
                            public void done(User returnedUser) {

                            }

                            @Override
                            public void done( List<ReminderObject> returnedUser) {

                            }

                            @Override
                            public void done() {

                            }

                        }, sp.getString("EMAIL", "not_found"));

                        new SweetAlertDialog(Reminder.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Successful")
                                .setContentText("Successfully added on to your appointment")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intentMain = new Intent(Reminder.this ,MainActivity.class);
                                        Reminder.this.startActivity(intentMain);
                                    }
                                })
                                .show();
                    } else {
                        new SweetAlertDialog(Reminder.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Something went wrong...")
                                .setContentText("Not acceptable format !!")
                                .show();
                    }
                }

            }
        });

        goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Reminder.this ,MainActivity.class);
                Reminder.this.startActivity(intentMain);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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