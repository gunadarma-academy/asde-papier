package com.papier.jurani;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Angellica on 3/21/2016.
 */
public class RandomTruthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.random_truth_toolbar);
        setSupportActionBar(toolbar);


    }
}
