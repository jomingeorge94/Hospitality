package com.example.jomin.hospitality;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;


public class Appointments extends ActionBarActivity {

    LinearLayout goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments);

        final SharedPreferences sp = getSharedPreferences("GLOB", MODE_PRIVATE);

        ServerRequests serverRequest = new ServerRequests(Appointments.this);
        serverRequest.getReminders(new GetUserCallback() {
            @Override
            public void done(final User returnedUser) {


            }

            @Override
            public void done( List<ReminderObject> returnedObj) {
                for(ReminderObject r : returnedObj){
                     /* Find Tablelayout defined in main.xml */
                    TableLayout tl = (TableLayout) findViewById(R.id.tablebody);
        /* Create a new row to be added. */
                    TableRow tr = new TableRow(Appointments.this);
                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                    tr.setLayoutParams(params);


                    TextView t1 = new TextView(Appointments.this);
                    TextView t2 = new TextView(Appointments.this);
                    TextView t3 = new TextView(Appointments.this);
                    TextView hidden = new TextView(Appointments.this);
                    TextView b = new TextView(Appointments.this);
                    t1.setText(r.date.substring(0,4) + "  |");
                    t2.setText(r.time.substring(0,4) + "   |");
                    t3.setText(r.type + "  |     ");
                    hidden.setText(String.valueOf(r.id));
                    hidden.setVisibility(View.GONE);
                    b.setText("   X   ");

                    t1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    t2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    t3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    hidden.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    // Listeners
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewGroup container = (ViewGroup) v.getParent();
                            int indexOfChild = container.indexOfChild(v);
                            TextView id = (TextView) container.getChildAt(indexOfChild+1);
                            ServerRequests serverRequest = new ServerRequests(Appointments.this);
                            serverRequest.postDeleteReminderDataInBackground(id.getText().toString(), new GetUserCallback() {
                                @Override
                                public void done(User returnedUser) {

                                }

                                @Override
                                public void done( List<ReminderObject> returnedUser) {

                                }

                                @Override
                                public void done() {
                                    finish();
                                    startActivity(getIntent());
                                }

                            });

                        }
                    });

                    tr.addView(t1);
                    tr.addView(t2);
                    tr.addView(t3);
                    tr.addView(b);
                    tr.addView(hidden);





                    tr.setLayoutParams(params);

                    tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                }
            }

            @Override
            public void done() {
            }
        }, sp.getString("EMAIL", "not_found"));





        goBack = (LinearLayout)findViewById(R.id.back_button);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Appointments.this ,MainActivity.class);
                Appointments.this.startActivity(intentMain);
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
