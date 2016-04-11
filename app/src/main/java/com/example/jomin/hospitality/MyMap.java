package com.example.jomin.hospitality;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jomin on 08/04/2016.
 */
public class MyMap extends ActionBarActivity {

    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        String s = getIntent().getStringExtra("SERVICE");
        String t = getIntent().getStringExtra("TEXT");

        int getting_Intent = getIntent().getIntExtra("IMAGE", 0);
        Drawable drawable_Resource = getResources().getDrawable(getting_Intent);

        Bundle bundle = new Bundle();
        bundle.putString("SERVICE", s);

        TextView text = (TextView)findViewById(R.id.map_text);
        text.setText(t);

        ImageView image = (ImageView)findViewById(R.id.service_image);
        image.setImageDrawable(drawable_Resource);

        Fragment l = new LocationFragment();
        l.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.lay, l).commit();

        LinearLayout goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(MyMap.this ,FindServices.class);
                MyMap.this.startActivity(intentMain);
            }
        });
    }

    public void onBackPressed() {
        return;
    }
}