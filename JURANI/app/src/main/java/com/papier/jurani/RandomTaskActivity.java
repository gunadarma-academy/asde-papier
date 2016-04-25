package com.papier.jurani;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RandomTaskActivity extends AppCompatActivity {

    private int i = 0;
    private int j = 0;
    private String [] player;
    private String [] dare = {"Impersonate...", "Tembak...", "Putusin...", "Cabut bulu kaki kanannya...",
            "Pakaikan lipstik ke...", "Peluk erat...", "Selama 5 menit, gendong...",
            "Gelitikin pinggang...", "Ikutin gerak-gerik...", "Terima pukulan dari...",
            "Rayu atau gombalin...", "Pukul kencang tangan...", "Ajak kencan si...",
            "Sampai makanannya abis, suapin...", "Nyanyiin lagu untuk...",
            "Sampai game selesai, pegang tangan...", "Bacain puisi untuk..."};
    private String [] truth = {"Kesan pertama kamu terhadap...", "Apa yang paling kamu nggak suka dari...", "Kenapa kamu suka sama...",
            "Kepada kamu, apa kesalahan terbesar yang pernah dilakukan oleh...", "Ke mana kamu bakal honeymoon kalo pergi sama...",
            "Ceritakan fakta memalukan yang kamu tahu tentang...", "Apa yang kamu lakukan kalo kamu terjebak di pulau sebulan bersama...",
            "Menurut kamu, apa kebiasaan paling menyebalkan dari...", "Kebohongan apa yang pernah kamu katakan ke...",
            "Sifat apa yang pengen kamu ubah dari...", "Ceritakan pengalaman memalukan kamu bersama...", "Ceritakan pengalaman berteman kamu dan...",
            "Kamu lebih pilih nolong kucing tenggelam atau kasih duit 100rb ke...", "Menurut kamu, cantik/ganteng kah si...",
            "Menurut kamu, lebih pinter kamu atau...", "Sifat apa yang paling kamu kagumi dari...", "Sampaikan semua hal yang kamu pendam selama ini terhadap..."};
    private String task; // utk kondisi truth or dare
    private String chosenPlayer;
    private String chosenTask;
    private String chosenTarget;
    private String val = "random verb"; // utk kondisi button
    private TextView up;
    private TextView middleTemp;
    private TextView middle;
    private TextView downTemp;
    private TextView down;
    private Button act;
    private Thread randVerb;
    private Thread randObj;
    private Typeface typeUp;
    private Typeface typeMiddle;
    private Typeface typeMiddleTemp;
    private Typeface typeDown;
    private Typeface typeDownTemp;
    private Typeface typeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_task_toolbar);
        setSupportActionBar(toolbar);

        player = getIntent().getStringArrayExtra("player");

        chosenPlayer = getIntent().getExtras().getString("chosen");

        task = getIntent().getExtras().getString("task");

        up = (TextView) findViewById(R.id.text_up);
        up.setText(chosenPlayer);
        typeUp = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        up.setTypeface(typeUp);

        middle = (TextView) findViewById(R.id.text_middle);
        typeMiddle = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        middle.setTypeface(typeMiddle);

        down = (TextView) findViewById(R.id.text_down);
        typeDown = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        down.setTypeface(typeDown);

        middleTemp = (TextView) findViewById(R.id.text_middle_temp);
        typeMiddleTemp = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        middleTemp.setTypeface(typeMiddleTemp);

        final Runnable runVerb = new Runnable() {
            @Override
            public void run() {
                while (i < dare.length) {
                    try {
                        Thread.sleep(30);
                        middleTemp.post(new Runnable() {
                            @Override
                            public void run() {
                                switch (task) {
                                    case "truth":
                                        middleTemp.setText(truth[i]);
                                        if (i == truth.length - 1) {
                                            if (i % 2 == 0) {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_up);
                                            } else {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_down);
                                            }
                                            i = 0;
                                        } else {
                                            if (i % 2 == 0) {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_up);
                                            } else {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_down);
                                            }
                                            i += 1;
                                        }
                                        break;
                                    case "dare":
                                        middleTemp.setText(dare[i]);
                                        if (i == dare.length - 1) {
                                            if (i % 2 == 0) {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_up);
                                            } else {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_down);
                                            }
                                            i = 0;
                                        } else {
                                            if (i % 2 == 0) {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_up);
                                            } else {
                                                middleTemp.setBackgroundResource(R.drawable.random_text_down);
                                            }
                                            i += 1;
                                        }
                                        break;
                                }

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        // run the random verb thread
        randVerb = new Thread(runVerb);
        randVerb.start();

        downTemp = (TextView) findViewById(R.id.text_down_temp);
        typeDownTemp = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        downTemp.setTypeface(typeDownTemp);
        final Runnable runObj = new Runnable() {
            @Override
            public void run() {
                while (j < player.length) {
                    try {
                        Thread.sleep(30);
                        downTemp.post(new Runnable() {
                            @Override
                            public void run() {
                                if (player[j].equals(chosenPlayer)) {
                                    if (j == player.length - 1) {
                                        j = 0;
                                    } else {
                                        j += 1;
                                    }
                                } else {
                                    downTemp.setText(player[j]);
                                    if (j == player.length - 1) {
                                        if (j % 2 == 0) {
                                            downTemp.setBackgroundResource(R.drawable.random_text_up);
                                        } else {
                                            downTemp.setBackgroundResource(R.drawable.random_text_down);
                                        }
                                        j = 0;
                                    } else {
                                        if (j % 2 == 0) {
                                            downTemp.setBackgroundResource(R.drawable.random_text_up);
                                        } else {
                                            downTemp.setBackgroundResource(R.drawable.random_text_down);
                                        }
                                        j += 1;
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

        act = (Button) findViewById(R.id.stop_button_random);
        typeButton = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        act.setTypeface(typeButton);
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (val) {
                    case "random verb":
                        randVerb.interrupt();
                        chosenTask = middleTemp.getText().toString();
                        middleTemp.setVisibility(View.GONE);
                        middle.setVisibility(View.VISIBLE);
                        middle.setText(chosenTask);
                        downTemp.setVisibility(View.VISIBLE);
                        val = "random object";
                        randObj = new Thread(runObj);
                        randObj.start();
                        //middleTemp -> gone (hapus)
                        //middle -> visible
                        //thread interrupt utk ambil tantangan trus ditampilin di middle
                        ////randVerb.interrupt();
                        ////chosenTask = middleTemp.getText().toString();
                        //val = "random object"
                        //downTemp -> visible
                        //start randObj thread
                        ////randObj = new Thread(runObj);
                        ////randObj.start();
                        break;
                    case "random object":
                        randObj.interrupt();
                        chosenTarget = downTemp.getText().toString();
                        downTemp.setVisibility(View.GONE);
                        down.setVisibility(View.VISIBLE);
                        down.setText(chosenTarget);
                        val = "end";
                        act.setText(R.string.action_next);
                        //downTemp -> gone (hapus)
                        //down -> visible
                        //thread interrupt utk ambil nama trus ditampilin di down
                        //val = end
                        //ubah tulisan "BERHENTI" di button jadi "LANJUTKAN"
                        break;
                    case "end":
                        finish(); //ulang permainan, kembali ke halaman awal
                        break;
                }
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
