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

/**
 * Created by Angellica on 3/20/2016.
 */
public class ChosenPlayerActivity extends AppCompatActivity {

    private String chosenName;
    private TextView theone;
    private Bundle str;
    private Button repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_player_toolbar);
        setSupportActionBar(toolbar);

        str = getIntent().getExtras();
        chosenName = str.getString("chosen");

        theone = (TextView) findViewById(R.id.chosen_name);
        theone.setText(chosenName);

        repeat = (Button) findViewById(R.id.repeat_button);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
