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
 * Created by Angellica on 3/19/2016.
 */
public class RandomPlayerActivity extends AppCompatActivity {

    private int i = 0;
    private String [] player;
    private String chosen;
    private TextView randName;
    private Button stop;
    private Thread runThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_player_toolbar);
        setSupportActionBar(toolbar);

        player = getIntent().getStringArrayExtra("player");
        randName = (TextView) findViewById(R.id.random_name);
        //randName.setText(player[0]);
        Runnable runName = new Runnable() {
            @Override
            public void run() {
                while (i < player.length) {
                    try {
                        Thread.sleep(250);
                        randName.post(new Runnable() {
                            @Override
                            public void run() {
                                randName.setText(player[i]);
                                if (i == player.length-1) {
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

        stop = (Button) findViewById(R.id.stop_button_player);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread.interrupt();
                chosen = randName.getText().toString();
                Intent goChoose = new Intent (getApplicationContext(), ChosenPlayerActivity.class);
                goChoose.putExtra("chosen", chosen);
                startActivity(goChoose);
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
