package com.unisagrado.appcompcrianca;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class RegisterPageTest {

    private RegisterPage registerPage;

    @Before
    public void setUp() {
        registerPage = Robolectric.buildActivity(RegisterPage.class).create().visible().get();
    }

    @Test
    public void testValidateEmail() {
        EditText emailEditText = registerPage.findViewById(R.id.signup_email);

        emailEditText.setText("");
        assertFalse(registerPage.validateEmail());

        emailEditText.setText("example@example.com");
        assertTrue(registerPage.validateEmail());
    }

    @Test
    public void testValidatePassword() {
        EditText passwordEditText = registerPage.findViewById(R.id.signup_password);

        passwordEditText.setText("");
        assertFalse(registerPage.validatePassword());

        passwordEditText.setText("password123");
        assertTrue(registerPage.validatePassword());
    }

    @Test
    public void testValidateUsername() {
        EditText usernameEditText = registerPage.findViewById(R.id.signup_username);

        usernameEditText.setText("");
        assertFalse(registerPage.validateUsername());

        usernameEditText.setText("user123");
        assertTrue(registerPage.validateUsername());
    }

}
