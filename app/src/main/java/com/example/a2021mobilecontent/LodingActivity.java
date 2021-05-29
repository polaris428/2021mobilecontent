package com.example.a2021mobilecontent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LodingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding_activity);



        Intent intent = new Intent(LodingActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}