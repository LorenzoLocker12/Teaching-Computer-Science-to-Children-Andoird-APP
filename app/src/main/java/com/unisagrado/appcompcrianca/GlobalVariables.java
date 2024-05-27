package com.unisagrado.appcompcrianca;

import android.app.Application;

public class GlobalVariables extends Application {

    private String userName;

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public GlobalVariables() {
    }
}
