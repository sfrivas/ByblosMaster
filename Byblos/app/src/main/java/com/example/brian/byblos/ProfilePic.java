package com.example.brian.byblos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Brian on 2/6/2016.
 */
public class ProfilePic extends Activity {

    private Button uploadBtn;
    private Button backBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.profile_pic);

        uploadBtn = (Button) findViewById(R.id.upload_photo);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfilePic.this, "Uploading...", Toast.LENGTH_LONG).show();
            }
        });

        backBtn = (Button) findViewById(R.id.back2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePic.this, CreditCard.class);
                startActivity(intent);
            }
        });

        nextBtn = (Button) findViewById(R.id.next2);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePic.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
