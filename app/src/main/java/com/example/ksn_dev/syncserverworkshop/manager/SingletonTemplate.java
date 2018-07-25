package com.example.ksn_dev.syncserverworkshop.manager;

import android.content.Context;

public class SingletonTemplate {
    private static SingletonTemplate ourInstance;
    private Context mContext;

    public static SingletonTemplate getInstance() {
        if (ourInstance == null) {
            ourInstance = new SingletonTemplate();
        }
        return ourInstance;
    }

    private SingletonTemplate() {
        mContext = Contextor.getInstance().getContext();
    }


}
