package com.unisagrado.appcompcrianca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VariaveisChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variaveis_choice);

        RelativeLayout sistemassBtn = findViewById(R.id.jogarBtn);
        RelativeLayout aprenderBtn = findViewById(R.id.aprender);

        sistemassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VariaveisChoice.this, VarGameStart.class);
                startActivity(intent);
            }
        });

        aprenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VariaveisChoice.this, ArqVideo.class);
                startActivity(i);
            }
        });


    }
}