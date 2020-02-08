package com.example.app2.CafeBazar.CafeModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CommentApp implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("star")
    private String star;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("app_id")
    private String appId;

    @SerializedName("like")
    private String like;

    @SerializedName("dislike")
    private String dislike;

    @SerializedName("user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.star);
        dest.writeString(this.userName);
    }

    public CommentApp() {
    }

    protected CommentApp(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.star = in.readString();
        this.userName = in.readString();
    }

    public static final Parcelable.Creator<CommentApp> CREATOR = new Parcelable.Creator<CommentApp>() {
        @Override
        public CommentApp createFromParcel(Parcel source) {
            return new CommentApp(source);
        }

        @Override
        public CommentApp[] newArray(int size) {
            return new CommentApp[size];
        }
    };
}
