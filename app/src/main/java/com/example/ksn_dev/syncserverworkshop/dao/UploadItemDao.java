package com.example.ksn_dev.syncserverworkshop.dao;

import com.google.gson.annotations.SerializedName;

public class UploadItemDao {
    @SerializedName("upload_result")
    private String uploadResult;

    public UploadItemDao() {

    }

    public String getUploadResult() {
        return uploadResult;
    }

    public void setUploadResult(String uploadResult) {
        this.uploadResult = uploadResult;
    }

}
