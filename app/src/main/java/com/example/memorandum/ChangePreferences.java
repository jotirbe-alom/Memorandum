package com.example.memorandum;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ChangePreferences extends AppCompatActivity {

    ImageView img1,img2,img3,img4;
    RelativeLayout rl1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_preferences);

        img1 = findViewById(R.id.image_view_1);
        img2 = findViewById(R.id.image_view_2);
        img3 = findViewById(R.id.image_view_3);
        img4 = findViewById(R.id.image_view_4);
        rl1 = findViewById(R.id.chatwindowlayout);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rl1.setBackgroundResource(R.drawable.chat_bg1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl1.setBackgroundResource(R.drawable.chat_bg2);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl1.setBackgroundResource(R.drawable.chat_bg3);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl1.setBackgroundResource(R.drawable.chat_bg4);
            }
        });

    }

}