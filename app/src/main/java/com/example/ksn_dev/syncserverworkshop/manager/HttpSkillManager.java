package com.example.ksn_dev.syncserverworkshop.manager;

import android.content.Context;

import com.example.ksn_dev.syncserverworkshop.dao.SkillItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.manager.http.SkillAPI;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpSkillManager {
    private static HttpSkillManager ourInstance;
    private Context mContext;
    private SkillAPI api;

    public static HttpSkillManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new HttpSkillManager();
        }
        return ourInstance;
    }

    private HttpSkillManager() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksnajaroon.com/nurse_note/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SkillAPI.class);
    }

    private Observable<SkillItemCollectionDao> skillRepos;

    public Observable<SkillItemCollectionDao> getSkillRepos() {
        return api.feedSkillItem();
    }

    public SkillAPI getApi() {
        return api;
    }

    public void setApi(SkillAPI api) {
        this.api = api;
    }
}
