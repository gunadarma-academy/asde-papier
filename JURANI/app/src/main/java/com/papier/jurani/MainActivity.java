package com.papier.jurani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int people = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        Toolbar toolbar = (Toolbar) findViewById(R.id.count_toolbar);
        setSupportActionBar(toolbar);

        final TextView count = (TextView) findViewById(R.id.count);
        Button plus = (Button) findViewById(R.id.plus_count);
        Button min = (Button) findViewById(R.id.min_count);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people += 1;
                if (people > 10) {
                    people = 10;
                    count.setText(Integer.toString(people));
                    Toast.makeText(getApplicationContext(), "Maksimum pemain adalah 10 orang.", Toast.LENGTH_SHORT).show();
                } else {
                    count.setText(Integer.toString(people));
                }
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people -= 1;
                if (people < 2) {
                    people = 2;
                    count.setText(Integer.toString(people));
                    Toast.makeText(getApplicationContext(), "Minimum pemain adalah 2 orang.", Toast.LENGTH_SHORT).show();
                } else {
                    count.setText(Integer.toString(people));
                }
            }
        });

        Button goToName = (Button) findViewById(R.id.next_button_count);
        goToName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToName = new Intent(getApplicationContext(), PlayerInputActivity.class);
                intentGoToName.putExtra("sum", people);
                startActivity(intentGoToName);
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
        if (id == R.id.action_help) {
            Intent goToHelp = new Intent (getApplicationContext(), HelpActivity.class);
            startActivity(goToHelp);
            return true;
        } else if (id == R.id.action_developer) {
            Intent goToDev = new Intent (getApplicationContext(), PengembangActivity.class);
            startActivity(goToDev);
            return true;
        } else if (id == R.id.action_reset) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}