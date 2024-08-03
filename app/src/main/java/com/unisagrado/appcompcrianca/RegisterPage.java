package com.unisagrado.appcompcrianca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    EditText  signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUsername() && validateEmail() && validatePassword()){
                    final String email = signupEmail.getText().toString();
                    final String username = signupUsername.getText().toString();
                    final String password = signupPassword.getText().toString();

                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(username);
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {

                            } else {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                                HelperClass helperClass = new HelperClass(username, email, password);
                                reference.child(username).setValue(helperClass);

                                Toast.makeText(RegisterPage.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(RegisterPage.this, "Erro ao verificar usuário", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
            }
        });



    }
    public Boolean validateEmail(){
        String val = signupEmail.getText().toString();
        if(val.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            signupEmail.setError("Email inválido");
            return false;
        }
        else{
            signupEmail.setError(null);
            return true;
        }

    }
    public Boolean validatePassword(){
        String val = signupPassword.getText().toString();
        if(val.isEmpty()){
            signupPassword.setError("O campo Senha não pode estar vazio");
            return false;
        }
        else{
            signupPassword.setError(null);
            return true;
        }

    }

    public Boolean validateUsername(){
        String val = signupUsername.getText().toString();
        if(val.isEmpty()){
            signupUsername.setError("O campo Usuário não pode estar vazio");
            return false;
        }
        else{
            signupUsername.setError(null);
            return true;
        }

    }

}