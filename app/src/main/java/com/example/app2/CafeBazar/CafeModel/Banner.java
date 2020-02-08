package com.example.app2.CafeBazar.CafeModel;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
