package com.example.jomin.hospitality;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UsefulLinks extends ActionBarActivity {

    LinearLayout goBack;
    ImageView nhschoice, firstaid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useful_links);

        goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(UsefulLinks.this ,MainActivity.class);
                UsefulLinks.this.startActivity(intentMain);
            }
        });


        nhschoice = (ImageView)findViewById(R.id.nhs_choices);
        nhschoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://www.nhs.uk/pages/home.aspx"));
                startActivity(viewIntent);
            }
        });

        firstaid = (ImageView)findViewById(R.id.first_aid);
        firstaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://www.nhs.uk/Conditions/Accidents-and-first-aid/Pages/Introduction.aspx"));
                startActivity(viewIntent);
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