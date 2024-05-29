package com.unisagrado.appcompcrianca;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Config.OLDEST_SDK})
public class LoginPageTest {

    private LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = Robolectric.buildActivity(LoginPage.class).create().get();
    }

    @Test
    public void testValidateUsername_EmptyUsername_ReturnsFalse() {
        EditText usernameEditText = loginPage.findViewById(R.id.login_username);
        usernameEditText.setText("");
        assertFalse(loginPage.validateUsername());
    }

    @Test
    public void testValidateUsername_NonEmptyUsername_ReturnsTrue() {
        EditText usernameEditText = loginPage.findViewById(R.id.login_username);
        usernameEditText.setText("username123");
        assertTrue(loginPage.validateUsername());
    }

    @Test
    public void testValidatePassword_EmptyPassword_ReturnsFalse() {
        EditText passwordEditText = loginPage.findViewById(R.id.login_password);
        passwordEditText.setText("");
        assertFalse(loginPage.validatePassword());
    }

    @Test
    public void testValidatePassword_NonEmptyPassword_ReturnsTrue() {
        EditText passwordEditText = loginPage.findViewById(R.id.login_password);
        passwordEditText.setText("password123");
        assertTrue(loginPage.validatePassword());
    }

}
