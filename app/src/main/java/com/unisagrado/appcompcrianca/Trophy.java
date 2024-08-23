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
    private GlobalVariables globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);
        setContentView(R.layout.trophy);

        // Initialize the GlobalVariables application instance
        globalVariables = (GlobalVariables) getApplicationContext();

        String userName = globalVariables.getUserName();

        ImageView imageVariables = findViewById(R.id.variaveisTrophy);
        ImageView btnLinguagens = findViewById(R.id.linguagensTrophy);
        ImageView imageBinary = findViewById(R.id.binarioTrophy);
        ImageView imageSystems = findViewById(R.id.sistemasTrophy);

        Button btnMenu = findViewById(R.id.btnMenu);

        // Check if the user is logged in
        if (globalVariables.isUserLoggedIn()) {
            // User is logged in, use Firebase
            databaseReference = FirebaseDatabase.getInstance().getReference("users");
            databaseReference.child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        if (dataSnapshot != null) {
                            Boolean trophyLanguages = dataSnapshot.child("trophyLanguages").getValue(Boolean.class);

                            if (trophyLanguages != null) {
                                if (trophyLanguages) {
                                    btnLinguagens.setImageResource(R.drawable.trophy);
                                } else {
                                    btnLinguagens.setImageResource(R.drawable.pontilhado);
                                }
                            }

                            Boolean trophyBinary = dataSnapshot.child("trophyBinary").getValue(Boolean.class);
                            if (trophyBinary != null) {
                                if (trophyBinary) {
                                    imageBinary.setImageResource(R.drawable.trophy);
                                } else {
                                    imageBinary.setImageResource(R.drawable.pontilhado);
                                }
                            }

                            Boolean trophyVar = dataSnapshot.child("trophyVariables").getValue(Boolean.class);
                            if (trophyVar != null) {
                                if (trophyVar) {
                                    imageVariables.setImageResource(R.drawable.trophy);
                                } else {
                                    imageVariables.setImageResource(R.drawable.pontilhado);
                                }
                            }

                            Boolean trophySystems = dataSnapshot.child("trophySystems").getValue(Boolean.class);
                            if (trophySystems != null) {
                                if (trophySystems) {
                                    imageSystems.setImageResource(R.drawable.trophy);
                                } else {
                                    imageSystems.setImageResource(R.drawable.pontilhado);
                                }
                            }
                        } else {
                            useOfflineData();
                        }
                    } else {
                        useOfflineData();
                    }
                }
            });
        } else {
            // User is not logged in, use the offline data
            useOfflineData();
        }

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trophy.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void useOfflineData() {
        ImageView imageVariables = findViewById(R.id.variaveisTrophy);
        ImageView btnLinguagens = findViewById(R.id.linguagensTrophy);
        ImageView imageBinary = findViewById(R.id.binarioTrophy);
        ImageView imageSystems = findViewById(R.id.sistemasTrophy);

        if (globalVariables.istLing()) {
            btnLinguagens.setImageResource(R.drawable.trophy);
        } else {
            btnLinguagens.setImageResource(R.drawable.pontilhado);
        }

        if (globalVariables.istBin()) {
            imageBinary.setImageResource(R.drawable.trophy);
        } else {
            imageBinary.setImageResource(R.drawable.pontilhado);
        }

        if (globalVariables.istArq()) {
            imageVariables.setImageResource(R.drawable.trophy);
        } else {
            imageVariables.setImageResource(R.drawable.pontilhado);
        }

        if (globalVariables.istSis()) {
            imageSystems.setImageResource(R.drawable.trophy);
        } else {
            imageSystems.setImageResource(R.drawable.pontilhado);
        }
    }
}
