package com.example.jomin.hospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Register extends ActionBarActivity {

    EditText fullname, emailAddress, password, retypePassword;
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fullname = (EditText)findViewById(R.id.userInput_FullName);
        emailAddress = (EditText)findViewById(R.id.userInput_EmailAddress);
        password = (EditText)findViewById(R.id.userInput_Password);

        registerButton = (Button)findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = fullname.getText().toString();
                String email = emailAddress.getText().toString();
                String storepassword = password.getText().toString();

                User user = new User(fullName, email, storepassword);

                registerUser(user);





            }
        });
    }

    private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

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
