package com.example.ksn_dev.syncserverworkshop.manager;

import android.content.Context;

public class Contextor {
    private static Contextor ourInstance;
    private Context mContext;

    public static Contextor getInstance() {
        if (ourInstance == null) {
            ourInstance = new Contextor();
        }
        return ourInstance;
    }

    private Contextor() {
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }


}
