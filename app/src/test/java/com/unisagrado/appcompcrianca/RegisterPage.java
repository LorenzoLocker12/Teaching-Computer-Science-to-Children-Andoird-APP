package com.unisagrado.appcompcrianca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.unisagrado.appcompcrianca.R;

public class RegisterPage extends AppCompatActivity {

    EditText  signupUsername, signupEmail, signupPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
    }

    public Boolean validateEmail(){
        String val = signupEmail.getText().toString();
        return !val.isEmpty();
    }

    public Boolean validatePassword(){
        String val = signupPassword.getText().toString();
        return !val.isEmpty();
    }

    public Boolean validateUsername(){
        String val = signupUsername.getText().toString();
        return !val.isEmpty();
    }
}
