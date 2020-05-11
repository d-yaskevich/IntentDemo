package com.daria.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.daria.intentdemo.models.User;
import com.daria.intentdemo.models.UserMessage;

public class MessageActivity extends AppCompatActivity {

    public static String NAME_KEY = "NAME_KEY";
    public static String AGE_KEY = "AGE_KEY";
    public static String MESSAGE_KEY = "MESSAGE_KEY";

    public static String USER_MESSAGE_KEY = "USER_MESSAGE_KEY";

    EditText nameET;
    NumberPicker ageNP;
    EditText messageET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //init views

        nameET = findViewById(R.id.etName);

        ageNP = findViewById(R.id.npAge);
        //settings NumberPicker
        ageNP.setMinValue(0);
        ageNP.setMaxValue(150);
        ageNP.setValue(18);
        ageNP.setWrapSelectorWheel(false);

        messageET = findViewById(R.id.etMessage);
    }

    public void onSendClick(View v){
        String name = nameET.getText().toString();
        int age = ageNP.getValue();
        String message = messageET.getText().toString();

        // using default constructor
//        User user = new User(name, age);
//        UserMessage userMessage = new UserMessage(user, message);

        // using custom constructor
        UserMessage userMessage = new UserMessage(name, age, message);

        Intent intent = new Intent();
        intent.putExtra(USER_MESSAGE_KEY, userMessage);

//        intent.putExtra(NAME_KEY, name);
//        intent.putExtra(AGE_KEY, age);
//        intent.putExtra(MESSAGE_KEY, message);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
