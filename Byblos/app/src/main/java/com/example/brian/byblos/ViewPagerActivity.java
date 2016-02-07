package com.example.brian.byblos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Brian on 2/6/2016.
 */
public class ViewPagerActivity extends AppCompatActivity{

    private Button backBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_crime_pager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_crime_view_pager);
        viewPager.setAdapter(new MyViewPagerAdapter(this));

        backBtn = (Button) findViewById(R.id.back3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this, ProfilePic.class);
                startActivity(intent);
            }
        });

        nextBtn = (Button) findViewById(R.id.next3);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this, Camera.class);
                startActivity(intent);
            }
        });
    }
}
