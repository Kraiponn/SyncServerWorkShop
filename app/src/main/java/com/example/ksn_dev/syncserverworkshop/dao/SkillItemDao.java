package com.example.ksn_dev.syncserverworkshop.dao;

import com.google.gson.annotations.SerializedName;

public class SkillItemDao {
    @SerializedName("id")       private int id;
    @SerializedName("title")    private String title;
    @SerializedName("detail")   private String detail;

    public SkillItemDao() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
