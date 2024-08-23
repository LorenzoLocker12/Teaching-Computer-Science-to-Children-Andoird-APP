package com.unisagrado.appcompcrianca;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton, userLogin;
    TextView signupRedirectText, forgotPasswordRedirectText;

    Dialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        userLogin = findViewById(R.id.login_user_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        forgotPasswordRedirectText = findViewById(R.id.forgotPasswordRedirectText);
        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();

        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.userlogin);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setCancelable(true);
                mDialog.show();

                mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Intent intent = new Intent(LoginPage.this, MainActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addGlobal = loginUsername.getText().toString();
                globalVariables.setUserName(addGlobal);
                globalVariables.setUserLoggedIn(true);
                if (!validatePassword() || !validateUsername()){

                } else {
                    checkUser();
                }

            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);

            }
        });

        forgotPasswordRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
                startActivity(intent);

            }
        });
    }

    public Boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if(val.isEmpty()){
            loginUsername.setError("O campo Usuário não pode estar vazio");
            return false;
        }
        else{
            loginUsername.setError(null);
            return true;
        }

    }
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("O campo Senha não pode estar vazio");
            return false;
        }
        else{
            loginPassword.setError(null);
            return true;
        }

    }

    public void checkUser(){
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        loginUsername.setError(null);
                        Intent intent = new Intent(LoginPage.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        loginUsername.setError("Usúario ou senha inválidos");
                        loginUsername.requestFocus();
                        loginPassword.setError("Usúario ou senha inválidos");
                        loginPassword.requestFocus();
                    }
                }
                else {
                    loginUsername.setError("Usuário não existe");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}