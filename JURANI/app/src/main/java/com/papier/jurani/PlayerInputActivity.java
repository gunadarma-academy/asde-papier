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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class PlayerInputActivity extends AppCompatActivity {

    private int total;
    private int i = 0;
    private String [] name;
    private TextView parentText;
    private TextView childText;
    private EditText inputName;
    private Button insert;
    private Typeface typeParent;
    private Typeface typeChild;
    private Typeface typeInsert;
    private Typeface buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.player_toolbar);
        setSupportActionBar(toolbar);

        total = getIntent().getIntExtra("sum", 2);

        parentText = (TextView) findViewById(R.id.player_parent_text);
        parentText.setText(String.valueOf(total-(i+1))+" more player to go");
        typeParent = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        parentText.setTypeface(typeParent);

        childText = (TextView) findViewById(R.id.player_child_text);
        childText.setText("Masukkan nama pemain ke-" + String.valueOf(i + 1) + ":");
        typeChild = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        childText.setTypeface(typeChild);

        name = new String[total];

        inputName = (EditText) findViewById(R.id.input_name);
        typeInsert = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        inputName.setTypeface(typeInsert);
        /**
        inputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (inputName.getText().toString().equals("") || inputName.getText().toString().equals(" ")) {
                        Toast.makeText(getApplicationContext(), "Masukkan nama pemain.", Toast.LENGTH_SHORT).show();
                    } else {
                        name[i] = inputName.getText().toString();
                        if (i == total-1) {
                            randomName();
                            //inputName.setImeOptions(EditorInfo.IME_ACTION_DONE);
                            //inputName.setImeActionLabel("label", EditorInfo.IME_ACTION_DONE);
                        } else {
                            i += 1;
                            childtext.setText(text+" "+String.valueOf(i+1)+" dari "+String.valueOf(total)+" pemain:");
                            inputName.setText("");
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        insert = (Button) findViewById(R.id.next_button_player);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomName();
            }
        });*/

        insert = (Button) findViewById(R.id.next_button_player);
        buttonInsert = Typeface.createFromAsset(getAssets(), "fonts/proxima_nova_bold.otf");
        insert.setTypeface(buttonInsert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputName.getText().toString().equals("") || inputName.getText().toString().equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Masukkan nama pemain.", Toast.LENGTH_SHORT).show();
                } else {
                    String tmp = inputName.getText().toString();
                    for (int a=0; a<name.length; a++) {
                        if (name[a] == null) {
                            name[i] = tmp;
                            if (i == total-1) {
                                randomName();
                            } else {
                                i += 1;
                                parentText.setText(String.valueOf(total-(i+1))+" more player to go");
                                childText.setText("Masukkan nama pemain ke-"+String.valueOf(i+1)+":");
                                inputName.setText("");
                            }
                            break;
                        } else {
                            if (tmp.toLowerCase().equals(name[a].toLowerCase())) {
                                Toast.makeText(getApplicationContext(), "Masukkan nama yang lain.", Toast.LENGTH_SHORT).show();
                                inputName.setText("");
                                break;
                            } else {
                                continue;
                            }
                        }
                    }

                    /*
                    name[i] = inputName.getText().toString();
                    if (i == total-1) {
                        randomName();
                    } else {
                        i += 1;
                        childtext.setText(text+" "+String.valueOf(i+1)+" dari "+String.valueOf(total)+" pemain:");
                        inputName.setText("");
                    }*/
                }
            }
        });

    }

    public void randomName() {
        Intent goRandom = new Intent(getApplicationContext(), RandomPlayerActivity.class);
        goRandom.putExtra("player", name);
        startActivity(goRandom);
        finish();
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
