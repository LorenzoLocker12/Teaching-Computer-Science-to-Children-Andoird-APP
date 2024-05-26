package com.unisagrado.appcompcrianca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {

    EditText  signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");

                    String email = signupEmail.getText().toString();
                    String username = signupUsername.getText().toString();
                    String password = signupPassword.getText().toString();

                    HelperClass helperClass = new HelperClass(username, email, password);
                    reference.child(username).setValue(helperClass);

                    Toast.makeText(RegisterPage.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                    startActivity(intent);
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
        if(val.isEmpty()){
            signupEmail.setError("O campo Email não pode estar vazio");
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
            signupUsername.setError("O campo Senha não pode estar vazio");
            return false;
        }
        else{
            signupUsername.setError(null);
            return true;
        }

    }
}