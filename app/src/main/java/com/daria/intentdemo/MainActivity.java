package com.daria.intentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daria.intentdemo.models.UserMessage;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NAME_KEY = "NAME_KEY";
    public static final String NUMBER_KEY = "NUMBER_KEY";

    private static final int MESSAGE_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial views
        Button sayHelloBtn = findViewById(R.id.btnSayHello);
        sayHelloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open HelloActivity
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);

                //set data
                EditText nameET = findViewById(R.id.etName);
                String name = nameET.getText().toString();
                intent.putExtra(NAME_KEY, name);

                startActivity(intent);
            }
        });

        Button getMessageBtn = findViewById(R.id.btnGetMessage);
        getMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open MessageActivity
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivityForResult(intent, MESSAGE_REQUEST_CODE);
            }
        });

        messageTV = findViewById(R.id.tvMessage);

        Button siteBtn = findViewById(R.id.btnSite);
        Button emailBtn = findViewById(R.id.btnEmail);
        Button phoneBtn = findViewById(R.id.btnPhone);
        Button messageBtn = findViewById(R.id.btnMessage);

        siteBtn.setOnClickListener(this);
        emailBtn.setOnClickListener(this);
        phoneBtn.setOnClickListener(this);
        messageBtn.setOnClickListener(this);
    }

    TextView messageTV;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MESSAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
//                    String name = data.getStringExtra(MessageActivity.NAME_KEY);
//                    int age = data.getIntExtra(MessageActivity.AGE_KEY, -1);
//                    String message = data.getStringExtra(MessageActivity.MESSAGE_KEY);
//
//                    messageTV.setText(name + ", " + age + ", message:\n" + message);

                    UserMessage userMessage = (UserMessage) data.getSerializableExtra(MessageActivity.USER_MESSAGE_KEY);

                    messageTV.setText(userMessage.getUser().getName() + ", "
                            + userMessage.getUser().getAge()
                            + ", message:\n" + userMessage.getMessage());
                } else {
                    Toast.makeText(this, "Data is null", Toast.LENGTH_LONG).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Snackbar.make(messageTV, "Canceled", 2000).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Uri data;
        switch (v.getId()) {
            case R.id.btnSite:
                intent.setAction(Intent.ACTION_VIEW);

                data = Uri.parse("https://www.google.com");
                intent.setData(data);
                break;
            case R.id.btnEmail:
                intent.setAction(Intent.ACTION_SENDTO);

                data = Uri.parse("mailto:some@gmail.com");
                intent.setData(data);
                break;
            case R.id.btnPhone:
                intent.setAction(Intent.ACTION_DIAL);

                data = Uri.parse("tel:+375333944116");
                intent.setData(data);
                break;
            case R.id.btnMessage:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Message");
                intent.setType("text/plain");
                break;
            default:
                break;
        }

        startActivity(intent);
    }
}
