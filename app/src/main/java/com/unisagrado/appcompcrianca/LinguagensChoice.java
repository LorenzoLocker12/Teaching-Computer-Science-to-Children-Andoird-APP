package com.unisagrado.appcompcrianca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class LinguagensChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguagens_choice);

        RelativeLayout sistemassBtn = findViewById(R.id.jogarBtn);
        RelativeLayout aprenderBtn = findViewById(R.id.aprender);

        sistemassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LinguagensChoice.this, StartGame.class);
                startActivity(intent);
            }
        });

        aprenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LinguagensChoice.this, LingVideo.class);
                startActivity(i);
            }
        });


    }
}