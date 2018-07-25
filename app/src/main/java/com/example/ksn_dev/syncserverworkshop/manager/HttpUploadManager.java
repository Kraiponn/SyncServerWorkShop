package com.example.ksn_dev.syncserverworkshop.manager;

import com.example.ksn_dev.syncserverworkshop.manager.http.GitHubUploadAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUploadManager {
    //http://10.6.50.20/android-projects/nurse_note/index.php
    private final String BASE_URL =
            "http://10.6.50.20/android-projects/nurse_note/";
    private static  HttpUploadManager ourInstance;
    private final GitHubUploadAPI api;

    public static HttpUploadManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new HttpUploadManager();
        }
        return ourInstance;
    }

    private HttpUploadManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(GitHubUploadAPI.class);
    }

    public GitHubUploadAPI getApi() {
        return api;
    }

}
