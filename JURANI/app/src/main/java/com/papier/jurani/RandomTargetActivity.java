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
 * Created by Angellica on 3/23/2016.
 */
public class RandomTargetActivity extends AppCompatActivity {

    private int i = 0;
    private String [] player;
    private String chosenPlayer;
    private String chosenTask;
    private String chosenTarget;
    private TextView thePlayer;
    private TextView theTask;
    private TextView randTarget;
    private Button stop;
    private Thread runThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_target_toolbar);
        setSupportActionBar(toolbar);

        player = getIntent().getStringArrayExtra("player");

        chosenPlayer = getIntent().getExtras().getString("chosen");
        thePlayer = (TextView) findViewById(R.id.first_text);
        thePlayer.setText(chosenPlayer);

        chosenTask = getIntent().getExtras().getString("task");
        theTask = (TextView) findViewById(R.id.second_text);
        theTask.setText(chosenTask);

        randTarget = (TextView) findViewById(R.id.random_target);

        Runnable runName = new Runnable() {
            @Override
            public void run() {
                while (i < player.length) {
                    try {
                        Thread.sleep(150);
                        randTarget.post(new Runnable() {
                            @Override
                            public void run() {
                                if (player[i].equals(chosenPlayer)) {
                                    if (i == player.length-1) {
                                        i = 0;
                                    } else {
                                        i += 1;
                                    }
                                } else {
                                    randTarget.setText(player[i]);
                                    if (i == player.length-1) {
                                        i = 0;
                                    } else {
                                        i += 1;
                                    }
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

        stop = (Button) findViewById(R.id.stop_button_random_target);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread.interrupt();
                chosenTarget = randTarget.getText().toString();
                Intent goChoose = new Intent(getApplicationContext(), ChosenTargetActivity.class);
                goChoose.putExtra("player", chosenPlayer);
                goChoose.putExtra("task", chosenTask);
                goChoose.putExtra("target", chosenTarget);
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
            Intent goToFirst = new Intent (getApplicationContext(), MainActivity.class);
            startActivity(goToFirst);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
