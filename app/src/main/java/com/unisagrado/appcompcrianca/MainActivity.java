package com.unisagrado.appcompcrianca;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNavigate = findViewById(R.id.btnJogar);
        Button btnTrophy = findViewById(R.id.btnTrofeus);


        btnTrophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Trophy.class);
                startActivity(intent);
            }
        });

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click, navigate to SecondActivity
                Intent intent = new Intent(MainActivity.this, opcoes.class);
                startActivity(intent);
            }
        });


    }
}
