package com.example.ksn_dev.syncserverworkshop.manager.http;

import com.example.ksn_dev.syncserverworkshop.dao.UploadItemDao;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GitHubUploadAPI {
    @Multipart
    @POST("index.php")
    Call<UploadItemDao> uploadImage(@Part MultipartBody.Part file);
}
