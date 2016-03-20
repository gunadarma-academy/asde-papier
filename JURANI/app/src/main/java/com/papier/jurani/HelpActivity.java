package com.papier.jurani;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Angellica on 3/19/2016.
 */
public class HelpActivity extends Activity {

    private Button closeHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        closeHelp = (Button) findViewById(R.id.close_button_help);
        closeHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
