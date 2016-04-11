package com.example.jomin.hospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Register extends ActionBarActivity {

    EditText fullname, emailAddress, password, retypePassword;
    Button registerButton;
    LinearLayout goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fullname = (EditText)findViewById(R.id.userInput_FullName);
        emailAddress = (EditText)findViewById(R.id.userInput_EmailAddress);
        password = (EditText)findViewById(R.id.userInput_Password);
        retypePassword = (EditText)findViewById(R.id.userInput_RetypePassword);
        registerButton = (Button)findViewById(R.id.register_button);
        goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Register.this ,Login.class);
                Register.this.startActivity(intentMain);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = fullname.getText().toString();
                String email = emailAddress.getText().toString();
                String storepassword = password.getText().toString();
                String storeretypepassword = retypePassword.getText().toString();

                User user = new User(fullName, email, storepassword);

                if (fullName.length() >0 && email.length() >0 && storepassword.length() >0 ) {

                    if (isEmailValid(email) == true) {

                        if (storepassword.matches(storepassword)) {
                            registerUser(user);
                        } else {
                            new SweetAlertDialog(Register.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Registration Failed")
                                    .setContentText("Password does not match ")
                                    .setConfirmText("Try Again !")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                                            Intent loginIntent = new Intent(Register.this, Register.class);
                                            startActivity(loginIntent);

                                        }
                                    })
                                    .show();
                        }

                    } else {
                        new SweetAlertDialog(Register.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Registration Failed")
                                .setContentText("Email is not valid ")
                                .setConfirmText("Try Again !")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        Intent loginIntent = new Intent(Register.this, Register.class);
                                        startActivity(loginIntent);

                                    }
                                })
                                .show();
                    }




                } else {
                    new SweetAlertDialog(Register.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Registration Failed")
                            .setContentText("You need to enter all the fields")
                            .setConfirmText("Try Again !")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    Intent loginIntent = new Intent(Register.this, Register.class);
                                    startActivity(loginIntent);

                                }
                            })
                            .show();
                }






            }
        });
    }

    private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done() {

                new SweetAlertDialog(Register.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Registered Successfully !!")
                        .setConfirmText("Sign In")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                Intent loginIntent = new Intent(Register.this, Login.class);
                                startActivity(loginIntent);

                            }
                        })
                        .show();

            }

            @Override
            public void done(ReminderObject returnedUser) {
                Log.i("Unused", "");
            }

            @Override
            public void done(User u) {
                Log.i("Unused", "");
            }
        });
    }

    //email validation check
    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
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
