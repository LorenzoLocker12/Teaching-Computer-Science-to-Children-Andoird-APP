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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Trophy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trophy);

        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();
        String userName = globalVariables.getUserName();

        ImageView btnLinguagens = findViewById(R.id.linguagensTrophy);
        ImageView imageBinary = findViewById(R.id.binarioTrophy);

        Button btnMenu = findViewById(R.id.btnMenu);


        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();

                Boolean trophyLanguages = dataSnapshot.child("trophyLanguages").getValue(Boolean.class);
                if(trophyLanguages){
                    btnLinguagens.setImageResource(R.drawable.trophy);
                }
                else {
                    btnLinguagens.setImageResource(R.drawable.pontilhado);
                }

                Boolean trophyBinary = dataSnapshot.child("trophyBinary").getValue(Boolean.class);
                if(trophyBinary){
                    imageBinary.setImageResource(R.drawable.trophy);
                }
                else {
                    imageBinary.setImageResource(R.drawable.pontilhado);
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
}