package com.papier.jurani;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Angellica on 3/19/2016.
 */
public class PlayerInputActivity extends AppCompatActivity {

    private int total;
    private int i = 0;
    private String [] name;
    private String text;
    private TextView childtext;
    private EditText inputName;
    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.player_toolbar);
        setSupportActionBar(toolbar);

        childtext = (TextView) findViewById(R.id.player_text);
        text = childtext.getText().toString();
        childtext.setText(text+" "+String.valueOf(i+1)+":");

        total = getIntent().getIntExtra("sum", 2); System.out.println(total);

        name = new String[total];

        inputName = (EditText) findViewById(R.id.input_name);

        insert = (Button) findViewById(R.id.next_button_player);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputName.getText().toString().equals("") || inputName.getText().toString().equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Masukkan nama pemain.", Toast.LENGTH_SHORT).show();
                } else {
                    name[i] = inputName.getText().toString();
                    if (i == total-1) {
                        randomName();
                    } else {
                        i += 1;
                        childtext.setText(text+" "+String.valueOf(i+1)+":");
                        inputName.setText("");
                    }
                }
            }
        });
    }

    public void randomName() {
        Intent goRandom = new Intent(getApplicationContext(), RandomPlayerActivity.class);
        goRandom.putExtra("player", name);
        startActivity(goRandom);
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
