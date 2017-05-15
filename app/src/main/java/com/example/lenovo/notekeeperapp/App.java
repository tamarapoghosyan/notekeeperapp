package com.example.lenovo.notekeeperapp;

import android.app.Application;

/**
 * Created by LENOVO on 06.05.2017.
 */
public class App extends Application {

    private static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
