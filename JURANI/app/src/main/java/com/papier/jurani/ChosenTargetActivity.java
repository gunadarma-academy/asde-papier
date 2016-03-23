package com.papier.jurani;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Angellica on 3/23/2016.
 */
public class ChosenTargetActivity extends AppCompatActivity {

    private String chosenPlayer;
    private String chosenTask;
    private String chosenTarget;
    private TextView thePlayer;
    private TextView theTask;
    private TextView theTarget;
    private Button repeatGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chosen_target_toolbar);
        setSupportActionBar(toolbar);

        chosenPlayer = getIntent().getExtras().getString("player");
        chosenTask = getIntent().getExtras().getString("task");
        chosenTarget = getIntent().getExtras().getString("target");

        thePlayer = (TextView) findViewById(R.id.player_text);
        thePlayer.setText(chosenPlayer);

        theTask = (TextView) findViewById(R.id.task_text);
        theTask.setText(chosenTask);

        theTarget = (TextView) findViewById(R.id.chosen_target);
        theTarget.setText(chosenTarget);

        repeatGame = (Button) findViewById(R.id.repeat_game_button);
        repeatGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
