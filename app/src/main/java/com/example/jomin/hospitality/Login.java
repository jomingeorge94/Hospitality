package com.example.jomin.hospitality;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Login extends ActionBarActivity {

    Button loginButton;
    EditText InputemailAddress, Inputpassword;
    TextView ForgotPassword, SignUp;
    UserLocalDrive userLocalDrive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        userLocalDrive = new UserLocalDrive(this);

        loginButton = (Button) findViewById(R.id.login_button);
        InputemailAddress = (EditText)findViewById(R.id.input_email_address);
        Inputpassword = (EditText)findViewById(R.id.input_password);
        ForgotPassword = (TextView)findViewById(R.id.forgot_password_button);
        SignUp = (TextView) findViewById(R.id.sign_up_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = InputemailAddress.getText().toString();
                String password = Inputpassword.getText().toString();

                User user = new User(email, password);
                authenticate(user);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Login.this ,Register.class);
                Login.this.startActivity(intentMain);

            }
        });
    }

    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                }
                else {
                       logUserIn(returnedUser);
                }
            }
        });
    }

    private void logUserIn(User returnedUser) {

        userLocalDrive.storeUserData(returnedUser);
        userLocalDrive.setUserLoggedIn(true);

        /*new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successfully Logged in !")
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intentMain = new Intent(Login.this, MainActivity.class);
                        Login.this.startActivity(intentMain);
                    }
                })
                .show();*/



        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Intent intentMain = new Intent(Login.this, MainActivity.class);
                Login.this.startActivity(intentMain);
            }
        });
        pDialog.setCancelable(false);
        pDialog.show();


    }



    private void showErrorMessage() {
        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .setConfirmText("Try Again")
                .show();
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
