package com.example.ksn_dev.syncserverworkshop.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemCollectionDao {
    @SerializedName("success")      private boolean success;
    @SerializedName("data")         private List<ItemDao> data;

    public ItemCollectionDao(){}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ItemDao> getData() {
        return data;
    }

    public void setData(List<ItemDao> data) {
        this.data = data;
    }
}
