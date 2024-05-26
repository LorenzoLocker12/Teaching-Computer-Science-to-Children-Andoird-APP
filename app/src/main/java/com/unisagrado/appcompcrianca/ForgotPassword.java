package com.unisagrado.appcompcrianca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity {

    EditText name, new_password, email;

    Button redefine_button;

    TextView backRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        new_password = findViewById(R.id.new_password);
        email = findViewById(R.id.email);
        redefine_button = findViewById(R.id.redefine_button);
        backRedirectText = findViewById(R.id.backRedirectText);


        redefine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(name.getText().toString(), email.getText().toString(), new_password.getText().toString());

            }
        });


        backRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                startActivity(intent);
            }
        });

    }

    private void updateData(String name, String email, String new_password) {

        HashMap User = new HashMap();
        User.put("password", new_password);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(name).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(validatePassword() && validateEmail() && validatePassword()){
                    if (task.isSuccessful()) {

                        Toast.makeText(ForgotPassword.this, "Senha Atualizada!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ForgotPassword.this, "Oops Algo Deu Errado!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
    public Boolean validateName(){
        String val = name.getText().toString();
        if(val.isEmpty()){
            name.setError("O campo Usuário não pode estar vazio");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }

    }
    public Boolean validateEmail(){
        String val = email.getText().toString();
        if(val.isEmpty()){
            email.setError("O campo Email não pode estar vazio");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }

    }
    public Boolean validatePassword(){
        String val = new_password.getText().toString();
        if(val.isEmpty()){
            new_password.setError("O campo Nova Senha não pode estar vazio");
            return false;
        }
        else{
            new_password.setError(null);
            return true;
        }

    }

}


