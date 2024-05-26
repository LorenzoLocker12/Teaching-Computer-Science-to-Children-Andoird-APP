package com.unisagrado.appcompcrianca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class opcoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes);

        RelativeLayout sistemassBtn = findViewById(R.id.sistemasBtn);
        RelativeLayout linguagensBtn = findViewById(R.id.linguagensBtn);
        Button btnReset = findViewById(R.id.btnReset);
        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();

        sistemassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(opcoes.this, sistemasChoice.class);
                startActivity(intent);
            }
        });

        linguagensBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(opcoes.this, LinguagensChoice.class);
                startActivity(intent);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });


    }

    private void showPopup() {
        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Os troféus serão resetados");

        alertDialogBuilder.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(opcoes.this, Trophy.class);
                startActivity(intent);
                globalVariables.setLinguagensTrophy(false);
            }
        });

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

