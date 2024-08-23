package com.unisagrado.appcompcrianca;

import android.app.Application;

public class GlobalVariables extends Application {

    // Existing GlobalVariables functionality
    private String userName;

    // Offline-related variables
    private boolean tLing = false;
    private boolean tBin = false;
    private boolean tArq = false;
    private boolean tSis = false;
    private boolean userLoggedIn = false; // To indicate if the user is logged in

    // Constructor (Optional if needed)
    public GlobalVariables() {
    }

    // Getters and Setters for GlobalVariables functionality
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getters and Setters for Offline-related variables
    public boolean istLing() {
        return tLing;
    }

    public void settLing(boolean tLing) {
        this.tLing = tLing;
    }

    public boolean istBin() {
        return tBin;
    }

    public void settBin(boolean tBin) {
        this.tBin = tBin;
    }

    public boolean istArq() {
        return tArq;
    }

    public void settArq(boolean tArq) {
        this.tArq = tArq;
    }

    public boolean istSis() {
        return tSis;
    }

    public void settSis(boolean tSis) {
        this.tSis = tSis;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }
}
