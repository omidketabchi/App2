package com.example.app2.CafeBazar.CafeModel;

import com.google.gson.annotations.SerializedName;

public class InstalledPackage {

    @SerializedName("package_name")
    private String packageName;

    @SerializedName("version")
    private int version;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
