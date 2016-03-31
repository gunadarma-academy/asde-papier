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
 * Created by Angellica on 3/21/2016.
 */
public class RandomTruthActivity extends AppCompatActivity {

    private int i = 0;
    private String [] player;
    private String [] truth = {"Kesan pertama kamu terhadap...", "Apa yang paling kamu nggak suka dari...", "Kenapa kamu suka sama..."};
    private String chosenPlayer;
    private String chosenTruth;
    private TextView randTruth;
    private Button stop;
    private Thread runThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_truth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_truth_toolbar);
        setSupportActionBar(toolbar);

        player = getIntent().getStringArrayExtra("player");

        chosenPlayer = getIntent().getExtras().getString("chosen");

        randTruth = (TextView) findViewById(R.id.random_truth);
        Runnable runName = new Runnable() {
            @Override
            public void run() {
                while (i < truth.length) {
                    try {
                        Thread.sleep(10);
                        randTruth.post(new Runnable() {
                            @Override
                            public void run() {
                                randTruth.setText(truth[i]);
                                if (i == truth.length-1) {
                                    i = 0;
                                } else {
                                    i += 1;
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        runThread = new Thread(runName);
        runThread.start();

        stop = (Button) findViewById(R.id.stop_button_random_truth);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread.interrupt();
                chosenTruth = randTruth.getText().toString();
                Intent goChoose = new Intent (getApplicationContext(), RandomTargetActivity.class);
                goChoose.putExtra("player", player);
                goChoose.putExtra("chosen", chosenPlayer);
                goChoose.putExtra("task", chosenTruth);
                startActivity(goChoose);
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
