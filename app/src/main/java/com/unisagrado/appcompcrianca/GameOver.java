package com.unisagrado.appcompcrianca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {
    TextView tvPoints, tvPersonalBest, textoFim;

    SharedPreferences sharedPreferences;

    ImageView overimg;

    ImageButton exit, retry;

    Button trofeuBtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int points = getIntent().getExtras().getInt("pontos");
        tvPoints = findViewById(R.id.tvPoints);
        textoFim = findViewById(R.id.textofim);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        overimg = findViewById(R.id.overimg);
        exit = findViewById(R.id.exit);
        retry = findViewById(R.id.retry);
        trofeuBtn = findViewById(R.id.trofeusBtn);

        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();

        exit.setVisibility(View.VISIBLE);
        retry.setVisibility(View.VISIBLE);

        tvPoints.setText("" + points);
        sharedPreferences = getSharedPreferences("pref", 0);

        int pointsSP = sharedPreferences.getInt("pointsSP", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(points > pointsSP){
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            editor.commit();
        }
        tvPersonalBest.setText("" + pointsSP);

        if(points == 8){
            overimg.setImageResource(R.drawable.trophy);
            textoFim.setText("Parabéns, você acertou tudo!");
            exit.setVisibility(View.INVISIBLE);
            retry.setVisibility(View.INVISIBLE);
            trofeuBtn.setVisibility(View.VISIBLE);
            globalVariables.setLinguagensTrophy(true);

        }
        else{
            overimg.setImageResource(R.drawable.game_over);
            textoFim.setText("Tente novamente!");
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOver.this, opcoes.class);
                startActivity(intent);
            }
        });

        trofeuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOver.this, Trophy.class);
                startActivity(intent);
            }
        });
    }

    public void restart(View view) {
        // Create an Intent object to launch StartGame Activity
        Intent intent = new Intent(GameOver.this, StartGame.class);
        startActivity(intent);
        // Finish GameOver Activity
        finish();
    }

}