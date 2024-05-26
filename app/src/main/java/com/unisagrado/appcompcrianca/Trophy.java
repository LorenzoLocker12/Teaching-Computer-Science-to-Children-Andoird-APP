package com.unisagrado.appcompcrianca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Trophy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trophy);

        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();

        ImageView btnLinguagens = findViewById(R.id.linguagensTrophy);

        Button btnMenu = findViewById(R.id.btnMenu);

        boolean linguagens = globalVariables.getLinguagensTrophy();


        if(linguagens){
            btnLinguagens.setImageResource(R.drawable.trophy);
        }
        else {
            btnLinguagens.setImageResource(R.drawable.pontilhado);
        }

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trophy.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}