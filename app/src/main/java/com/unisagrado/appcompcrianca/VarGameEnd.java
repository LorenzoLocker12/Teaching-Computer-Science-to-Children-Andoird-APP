package com.unisagrado.appcompcrianca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class VarGameEnd extends AppCompatActivity {

    private TextView tvPoints, textoFim;
    private ImageView overimg;
    private ImageButton exit, retry;
    private Button trofeuBtn;
    private GlobalVariables globalVariables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_soft_game_end);

        globalVariables = (GlobalVariables) getApplicationContext();

        tvPoints = findViewById(R.id.tvPoints);
        textoFim = findViewById(R.id.textofim);
        overimg = findViewById(R.id.overimg);
        exit = findViewById(R.id.exit);
        retry = findViewById(R.id.retry);
        trofeuBtn = findViewById(R.id.trofeusBtn);

        int points = getIntent().getExtras().getInt("pontos");
        tvPoints.setText(String.valueOf(points));

        exit.setVisibility(View.VISIBLE);
        retry.setVisibility(View.VISIBLE);

        boolean trophyLanguages;

        if (points == 6) {
            overimg.setImageResource(R.drawable.trophy);
            textoFim.setText("Parabéns, você acertou tudo!");
            exit.setVisibility(View.INVISIBLE);
            retry.setVisibility(View.INVISIBLE);
            trofeuBtn.setVisibility(View.VISIBLE);
            trophyLanguages = true;
            globalVariables.settArq(true);
            updateData(trophyLanguages);
        } else {
            overimg.setImageResource(R.drawable.game_over);
            textoFim.setText("Tente novamente!");
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VarGameEnd.this, opcoes.class);
                startActivity(intent);
            }
        });

        trofeuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VarGameEnd.this, Trophy.class);
                startActivity(intent);
            }
        });
    }

    public void restart(View view) {
        Intent intent = new Intent(VarGameEnd.this, VarGameStart.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        Intent intent = new Intent(VarGameEnd.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateData(boolean trophyLanguages) {
        String userName = globalVariables.getUserName();

        if (userName == null || userName.isEmpty()) {
            Toast.makeText(this, "Erro: Nome de usuário não encontrado.", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, Object> trophy = new HashMap<>();
        trophy.put("trophyVariables", trophyLanguages);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userName).updateChildren(trophy).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(VarGameEnd.this, "Parabéns!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VarGameEnd.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
