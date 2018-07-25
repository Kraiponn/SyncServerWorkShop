package com.example.ksn_dev.syncserverworkshop.manager.http;

import com.example.ksn_dev.syncserverworkshop.dao.ItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.dao.SkillItemCollectionDao;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.POST;

public interface GitHubItemAPI {
    @POST("list")
    Observable<ItemCollectionDao> feedAt500px();
}
