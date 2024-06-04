package com.unisagrado.appcompcrianca;

public class HelperClass {
    String username, email, password;

    boolean trophyLanguages, trophyBinary, trophySystems, trophyVariables, xerequinha;

    public boolean isXerequinha() {
        return xerequinha;
    }

    public void setXerequinha(boolean xerequinha) {
        this.xerequinha = xerequinha;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isTrophyLanguages() {
        return trophyLanguages;
    }

    public void setTrophyLanguages(boolean trophyLanguages) {
        this.trophyLanguages = trophyLanguages;
    }

    public boolean isTrophyBinary() {
        return trophyBinary;
    }

    public void setTrophyBinary(boolean trophyBinary) {
        this.trophyBinary = trophyBinary;
    }

    public boolean isTrophySystems() {
        return trophySystems;
    }

    public void setTrophySystems(boolean trophySystems) {
        this.trophySystems = trophySystems;
    }

    public boolean isTrophyVariables() {
        return trophyVariables;
    }

    public void setTrophyVariables(boolean trophyVariables) {
        this.trophyVariables = trophyVariables;
    }

    public HelperClass(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.trophyLanguages = false;
        this.trophyBinary = false;
        this.trophySystems = false;
        this.trophyVariables = false;
    }
    public HelperClass() {
    }
}
