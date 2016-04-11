package com.example.jomin.hospitality;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CallForHelp extends ActionBarActivity {
    
    LinearLayout goBack;
    ImageButton call_999, call_111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_for_help);

        goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(CallForHelp.this ,MainActivity.class);
                CallForHelp.this.startActivity(intentMain);
            }
        });

        call_999 = (ImageButton)findViewById(R.id.call_999);
        call_999.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare and parse the number ot be called from a string
                Uri number = Uri.parse("tel:999");
                // Create intent object to perform call
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                // Begin the intent to start the call
                startActivity(callIntent);
            }
        });
        call_111 = (ImageButton)findViewById(R.id.call_111);
        call_111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare and parse the number ot be called from a string
                Uri number = Uri.parse("tel:111");
                // Create intent object to perform call
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                // Begin the intent to start the call
                startActivity(callIntent);
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