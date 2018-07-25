package com.example.ksn_dev.syncserverworkshop.manager;

import android.content.Context;

import com.example.ksn_dev.syncserverworkshop.dao.SkillItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.manager.http.GitHubItemAPI;
import com.example.ksn_dev.syncserverworkshop.manager.http.SkillAPI;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpItemManager {
    private static HttpItemManager ourInstance;
    private Context mContext;
    private GitHubItemAPI api;

    public static HttpItemManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new HttpItemManager();
        }
        return ourInstance;
    }

    private HttpItemManager() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nuuneoi.com/courses/500px/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(GitHubItemAPI.class);
    }

    public GitHubItemAPI getApi() {
        return api;
    }

    public void setApi(GitHubItemAPI api) {
        this.api = api;
    }

}
