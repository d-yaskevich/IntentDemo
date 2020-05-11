package com.daria.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        //initial views
        TextView nameTV = findViewById(R.id.tvName);

        //get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.NAME_KEY);

        //show data
        nameTV.setText("Hello, " + name + "!");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
