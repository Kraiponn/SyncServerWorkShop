package com.example.ksn_dev.syncserverworkshop.manager;

import android.content.Context;

import com.example.ksn_dev.syncserverworkshop.manager.http.UplaodApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUploadFile {
    private static HttpUploadFile ourInstance;
    private Context mContext;
    private UplaodApi api;

    public static HttpUploadFile getInstance() {
        if (ourInstance == null) {
            ourInstance = new HttpUploadFile();
        }
        return ourInstance;
    }

    private HttpUploadFile() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.6.50.20/android-projects/nurse_note/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(UplaodApi.class);
    }


    public UplaodApi getApi() {
        return api;
    }

    public void setApi(UplaodApi api) {
        this.api = api;
    }
}
