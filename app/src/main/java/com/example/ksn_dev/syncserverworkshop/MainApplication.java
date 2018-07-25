package com.example.ksn_dev.syncserverworkshop;

import android.app.Application;

import com.example.ksn_dev.syncserverworkshop.manager.Contextor;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
