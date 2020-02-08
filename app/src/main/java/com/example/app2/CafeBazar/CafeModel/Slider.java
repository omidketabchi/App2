package com.example.app2.CafeBazar.CafeModel;

import com.google.gson.annotations.SerializedName;

public class Slider {

    // asami dakhel SerializedName bayad ba chizi ke az server miyad barabar bashad.

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
