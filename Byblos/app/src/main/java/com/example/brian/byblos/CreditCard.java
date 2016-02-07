package com.example.brian.byblos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Brian on 2/6/2016.
 */
public class CreditCard extends Activity {

    private Button backBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.credit_card);

        backBtn = (Button) findViewById(R.id.back5);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditCard.this, AccountSetup.class);
                startActivity(intent);
            }
        });

        nextBtn = (Button) findViewById(R.id.next5);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditCard.this, ProfilePic.class);
                startActivity(intent);
            }
        });

    }
}
