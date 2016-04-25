package com.papier.jurani;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RandomPlayerActivity extends AppCompatActivity {

    private int i = 0;
    private String [] player;
    private String chosenPlayer;
    private TextView text1;
    private TextView text2;
    private TextView randName;
    private Button stop;
    private Thread runThread;
    private Typeface type1;
    private Typeface type2;
    private Typeface rand;
    private Typeface buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_player_toolbar);
        setSupportActionBar(toolbar);

        text1 = (TextView) findViewById(R.id.roll_text_1);
        type1 = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        text1.setTypeface(type1);

        text2 = (TextView) findViewById(R.id.roll_text_2);
        type2 = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        text2.setTypeface(type2);

        player = getIntent().getStringArrayExtra("player");

        randName = (TextView) findViewById(R.id.random_name);
        rand = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        randName.setTypeface(rand);

        Runnable runName = new Runnable() {
            @Override
            public void run() {
                while (i < player.length) {
                    try {
                        Thread.sleep(30);
                        randName.post(new Runnable() {
                            @Override
                            public void run() {
                                randName.setText(player[i]);
                                if (i == player.length-1) {
                                    if (i%2 == 0) {
                                        randName.setBackgroundResource(R.drawable.random_text_up);
                                    } else {
                                        randName.setBackgroundResource(R.drawable.random_text_down);
                                    }
                                    i = 0;
                                } else {
                                    if (i%2 == 0) {
                                        randName.setBackgroundResource(R.drawable.random_text_up);
                                    } else {
                                        randName.setBackgroundResource(R.drawable.random_text_down);
                                    }
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

        stop = (Button) findViewById(R.id.stop_button_random_player);
        buttonStop = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        stop.setTypeface(buttonStop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread.interrupt();
                chosenPlayer = randName.getText().toString();
                Intent goChoose = new Intent (getApplicationContext(), ChosenPlayerActivity.class);
                goChoose.putExtra("player", player);
                goChoose.putExtra("chosen", chosenPlayer);
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
            Intent goToDev = new Intent (getApplicationContext(), DeveloperActivity.class);
            startActivity(goToDev);
            return true;
        } else if (id == R.id.action_reset) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakah kamu yakin ingin me-reset permainan?").setTitle("Reset");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent goToFirst = new Intent(getApplicationContext(), MainActivity.class);
                    goToFirst.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah kamu yakin ingin keluar dari aplikasi ini?").setTitle("Keluar");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent close = new Intent(getApplicationContext(), CloseAppActivity.class);
                close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(close);
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
    }
}
