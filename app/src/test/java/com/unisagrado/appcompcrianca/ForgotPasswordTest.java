package com.unisagrado.appcompcrianca;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ForgotPasswordTest {

    private ForgotPassword forgotPasswordActivity;

    @Before
    public void setUp() {
        forgotPasswordActivity = Robolectric.buildActivity(ForgotPassword.class).create().get();
    }


    @Test
    public void testValidateName() {
        assertFalse(forgotPasswordActivity.validateName());

        forgotPasswordActivity.name.setText("username");

        assertTrue(forgotPasswordActivity.validateName());
    }

    @Test
    public void testValidateEmail() {
        assertFalse(forgotPasswordActivity.validateEmail());

        forgotPasswordActivity.email.setText("email@example.com");

        assertTrue(forgotPasswordActivity.validateEmail());
    }

    @Test
    public void testValidatePassword() {
        // Test empty password
        assertFalse(forgotPasswordActivity.validatePassword());

        // Set password text
        forgotPasswordActivity.new_password.setText("newpassword");

        // Test valid password
        assertTrue(forgotPasswordActivity.validatePassword());
    }
}
