package com.unisagrado.appcompcrianca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Trophy extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trophy);

        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();
        String userName = globalVariables.getUserName();

        ImageView btnLinguagens = findViewById(R.id.linguagensTrophy);
        ImageView imageBinary = findViewById(R.id.binarioTrophy);
        ImageView imageSystems = findViewById(R.id.sistemasTrophy);

        Button btnMenu = findViewById(R.id.btnMenu);


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot != null) {
                        Boolean trophyLanguages = dataSnapshot.child("trophyLanguages").getValue(Boolean.class);

                        if (trophyLanguages != null) {
                            if(trophyLanguages){
                                btnLinguagens.setImageResource(R.drawable.trophy);
                            } else {
                                btnLinguagens.setImageResource(R.drawable.pontilhado);
                            }
                        }

                        Boolean trophyBinary = dataSnapshot.child("trophyBinary").getValue(Boolean.class);
                        if (trophyBinary != null) {
                            if(trophyBinary){
                                imageBinary.setImageResource(R.drawable.trophy);
                            } else {
                                imageBinary.setImageResource(R.drawable.pontilhado);
                            }
                        }

                        Boolean trophySystems = dataSnapshot.child("trophySystems").getValue(Boolean.class);
                        if (trophySystems != null) {
                            if(trophySystems){
                                imageSystems.setImageResource(R.drawable.trophy);
                            } else {
                                imageSystems.setImageResource(R.drawable.pontilhado);
                            }
                        }
                    } else {

                    }
                } else {
                }
            }
        });


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trophy.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public DatabaseReference getDatabaseReference(){
        return databaseReference;
    }
}