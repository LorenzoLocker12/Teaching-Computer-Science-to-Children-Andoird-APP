package com.unisagrado.appcompcrianca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class sistemasChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistemas_choice);

        RelativeLayout sistemassBtn = findViewById(R.id.jogarBtn);

        sistemassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sistemasChoice.this, sistemasChoice.class);
                startActivity(intent);
            }
        });
    }
}