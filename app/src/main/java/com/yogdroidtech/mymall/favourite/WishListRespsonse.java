
package com.yogdroidtech.mymall.favourite;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yogdroidtech.mymall.register.Datum;

public class WishListRespsonse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<com.yogdroidtech.mymall.favourite.Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<com.yogdroidtech.mymall.favourite.Datum> getData() {
        return data;
    }

    public void setData(List<com.yogdroidtech.mymall.favourite.Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
