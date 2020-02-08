package com.example.app2.CafeBazar.CafeModel;

import com.google.gson.annotations.SerializedName;

public class Cat {
    @SerializedName("cat_name")
    private String catName;

    @SerializedName("cat_icon")
    private String catIcon;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon;
    }
}
