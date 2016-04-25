package com.papier.jurani;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CloseAppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}
