package com.example.app2.Alibaba.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileModel implements Parcelable {

    private String title;
    private String value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.value);
    }

    public ProfileModel() {
    }

    protected ProfileModel(Parcel in) {
        this.title = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<ProfileModel> CREATOR = new Parcelable.Creator<ProfileModel>() {
        @Override
        public ProfileModel createFromParcel(Parcel source) {
            return new ProfileModel(source);
        }

        @Override
        public ProfileModel[] newArray(int size) {
            return new ProfileModel[size];
        }
    };
}
