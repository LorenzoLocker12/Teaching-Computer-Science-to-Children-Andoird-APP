package com.unisagrado.appcompcrianca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class opcoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes);

        RelativeLayout sistemassBtn = findViewById(R.id.sistemasBtn);
        RelativeLayout linguagensBtn = findViewById(R.id.linguagensBtn);
        RelativeLayout binarioBtn = findViewById(R.id.binarioBtn);
        Button btnReset = findViewById(R.id.btnReset);

        binarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opcoes.this, BInariosChoice.class);
                startActivity(intent);
            }
        });

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
                updateDataa(false, false, false, false);
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

    public void updateDataa(boolean trophyLanguages,boolean trophyBinary, boolean trophySystems, boolean trophyVariables){
        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();
        HashMap trophy = new HashMap();
        trophy.put("trophyLanguages", trophyLanguages);
        trophy.put("trophyBinary", trophyBinary);
        trophy.put("trophySystems", trophySystems);
        trophy.put("trophyVariables", trophyVariables);
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(globalVariables.getUserName()).updateChildren(trophy);

    }
}

