package com.example.ksn_dev.syncserverworkshop.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SkillItemCollectionDao {
    @SerializedName("err")      private String err;
    @SerializedName("data")     private List<SkillItemDao> data;

    public SkillItemCollectionDao(){}

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public List<SkillItemDao> getData() {
        return data;
    }

    public void setData(List<SkillItemDao> data) {
        this.data = data;
    }
}
