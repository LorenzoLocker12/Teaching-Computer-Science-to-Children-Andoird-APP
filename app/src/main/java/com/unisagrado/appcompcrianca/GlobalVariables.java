package com.unisagrado.appcompcrianca;

import android.app.Application;

public class GlobalVariables extends Application {

    private static GlobalVariables instance;
    private static boolean linguagensTrophy = false;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public boolean getLinguagensTrophy() {
        return linguagensTrophy;
    }

    public void setLinguagensTrophy(boolean linguagensTrophy) {
        this.linguagensTrophy = linguagensTrophy;
    }


}
