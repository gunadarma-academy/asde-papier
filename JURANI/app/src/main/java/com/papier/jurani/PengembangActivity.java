package com.papier.jurani;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Angellica on 3/19/2016.
 */
public class PengembangActivity extends Activity {

    private Button closeDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pengembang);

        closeDev = (Button) findViewById(R.id.close_button_dev);
        closeDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
