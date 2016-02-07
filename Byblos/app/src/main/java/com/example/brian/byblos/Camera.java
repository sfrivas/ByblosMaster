package com.example.brian.byblos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Brian on 2/6/2016.
 */
public class Camera extends Activity {

    private ImageView barScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ready_to_sell);

        barScan = (ImageView) findViewById(R.id.scan_btn);

    }
}
