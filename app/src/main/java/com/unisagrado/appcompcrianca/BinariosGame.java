package com.unisagrado.appcompcrianca;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class BinariosGame extends AppCompatActivity {

    public boolean allCorrect;
    TextView number1, number2, number3, number4;
    EditText input1, input2, input3, input4;
    Button evaluateButton, infoBtn;

    Dialog mDialog;
    int[] numbers = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_binarios_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.binarypopup);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        if (findViewById(R.id.number1) != null) {
            number1 = findViewById(R.id.number1);
            number2 = findViewById(R.id.number2);
            number3 = findViewById(R.id.number3);
            number4 = findViewById(R.id.number4);

            input1 = findViewById(R.id.input1);
            input2 = findViewById(R.id.input2);
            input3 = findViewById(R.id.input3);
            input4 = findViewById(R.id.input4);

            infoBtn = findViewById(R.id.infoBtn);
            evaluateButton = findViewById(R.id.evaluateButton);

            // Continuar com as inicializações e configurações de listeners
        } else {
            Log.e("BinariosGame", "TextViews not found in layout");
        }
        generateRandomNumbers();

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAnswers();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });
    }

    public void generateRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            numbers[i] = random.nextInt(10) + 1;
        }


        number1.setText("Numero: " + String.valueOf(numbers[0]));
        number2.setText("Numero: " + String.valueOf(numbers[1]));
        number3.setText("Numero: " + String.valueOf(numbers[2]));
        number4.setText("Numero: " + String.valueOf(numbers[3]));
    }

    public void evaluateAnswers() {
        String[] userInputs = new String[4];
        userInputs[0] = input1.getText().toString();
        userInputs[1] = input2.getText().toString();
        userInputs[2] = input3.getText().toString();
        userInputs[3] = input4.getText().toString();

        boolean allCorrect = true;

        for (int i = 0; i < 4; i++) {
            if (!userInputs[i].equals(Integer.toBinaryString(numbers[i]))) {
                allCorrect = false;
                switch (i) {
                    case 0:
                        input1.setError("Número Binário incorreto");
                        input1.requestFocus();
                        break;
                    case 1:
                        input2.setError("Número Binário incorreto");
                        input2.requestFocus();
                        break;
                    case 2:
                        input3.setError("Número Binário incorreto");
                        input3.requestFocus();
                        break;
                    case 3:
                        input4.setError("Número Binário incorreto");
                        input4.requestFocus();
                        break;
                }
            } else {
                switch (i) {
                    case 0:
                        input1.setError(null);
                        break;
                    case 1:
                        input2.setError(null);
                        break;
                    case 2:
                        input3.setError(null);
                        break;
                    case 3:
                        input4.setError(null);
                        break;
                }
            }
        }

        if (allCorrect) {
            allCorrect = true;
            updateData(true);
            Intent intent = new Intent(BinariosGame.this, Trophy.class);
            startActivity(intent);
        } else {
            allCorrect = false;
            Toast.makeText(this, "Algumas respostas estão incorretas. Verifique os campos destacados.", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateData(boolean trophyLanguages){
        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();
        HashMap trophy = new HashMap();
        trophy.put("trophyBinary", trophyLanguages);
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(globalVariables.getUserName()).updateChildren(trophy).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    Toast.makeText(BinariosGame.this, "Parabéns!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BinariosGame.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}