package com.example.app2.CafeBazar.CafeModel;

import com.google.gson.annotations.SerializedName;

public class Slide {

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @SerializedName("slide")
    private String imgUrl;
}
