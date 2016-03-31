package com.papier.jurani;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    private String [] player;
    private String chosenPlayer;
    private TextView chosenText;
    private Button repeat;
    private Button truth;
    private Button dare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_player_toolbar);
        setSupportActionBar(toolbar);

        player = getIntent().getStringArrayExtra("player");

        chosenPlayer = getIntent().getExtras().getString("chosen");

        chosenText = (TextView) findViewById(R.id.chosen_name);
        chosenText.setText(chosenPlayer);

        repeat = (Button) findViewById(R.id.repeat_button);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        truth = (Button) findViewById(R.id.truth_button);
        truth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTruth = new Intent (getApplicationContext(), RandomTruthActivity.class);
                goToTruth.putExtra("player", player);
                goToTruth.putExtra("chosen", chosenPlayer);
                startActivity(goToTruth);
                finish();
            }
        });

        dare = (Button) findViewById(R.id.dare_button);
        dare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDare = new Intent (getApplicationContext(), RandomDareActivity.class);
                goToDare.putExtra("player", player);
                goToDare.putExtra("chosen", chosenPlayer);
                startActivity(goToDare);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakah kamu yakin ingin me-reset permainan?").setTitle("Reset");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent goToFirst = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToFirst);
                    finish();
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    closeContextMenu();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
