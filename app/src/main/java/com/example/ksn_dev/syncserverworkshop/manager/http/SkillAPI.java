package com.example.ksn_dev.syncserverworkshop.manager.http;

import com.example.ksn_dev.syncserverworkshop.dao.ItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.dao.SkillItemCollectionDao;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SkillAPI {
    @POST("index.php")
    Observable<SkillItemCollectionDao> feedSkillItem();

    @POST("list")
    Observable<ItemCollectionDao> feedAt500px();

    @POST("index.php")
    Single<SkillItemCollectionDao> feedSingleSkillItem();

    @POST("index.php")
    Call<SkillItemCollectionDao> feedByRetrofit();
}
