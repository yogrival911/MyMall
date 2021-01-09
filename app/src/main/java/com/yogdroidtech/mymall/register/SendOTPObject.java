
package com.yogdroidtech.mymall.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendOTPObject {
    public SendOTPObject(String phone, String signature) {
        this.phone = phone;
        this.signature = signature;
    }

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("signature")
    @Expose
    private String signature;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
