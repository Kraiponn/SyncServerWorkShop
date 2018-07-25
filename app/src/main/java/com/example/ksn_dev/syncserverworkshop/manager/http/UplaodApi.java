package com.example.ksn_dev.syncserverworkshop.manager.http;

import com.example.ksn_dev.syncserverworkshop.dao.ItemUpload;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UplaodApi {
    @Multipart
    @POST("uploadimage.php")
    Call<ItemUpload> uploadImage(@Part MultipartBody.Part img);
}
