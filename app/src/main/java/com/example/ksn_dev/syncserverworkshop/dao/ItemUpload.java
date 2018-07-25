package com.example.ksn_dev.syncserverworkshop.dao;

import com.google.gson.annotations.SerializedName;

public class ItemUpload {
    @SerializedName("err_flag")
    private String errFlag;

    @SerializedName("err_msg")
    private String errMsg;

    public ItemUpload() {
    }

    public String getErrFlag() {
        return errFlag;
    }

    public void setErrFlag(String errFlag) {
        this.errFlag = errFlag;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
