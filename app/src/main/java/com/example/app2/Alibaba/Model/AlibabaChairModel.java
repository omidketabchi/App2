package com.example.app2.Alibaba.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlibabaChairModel implements Parcelable {

    private String chair;
    private String status;

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AlibabaChairModel() {

    }

    public AlibabaChairModel(Parcel in) {
        chair = in.readString();
        status = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chair);
        dest.writeString(status);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AlibabaChairModel> CREATOR = new Parcelable.Creator<AlibabaChairModel>() {
        @Override
        public AlibabaChairModel createFromParcel(Parcel in) {
            return new AlibabaChairModel(in);
        }

        @Override
        public AlibabaChairModel[] newArray(int size) {
            return new AlibabaChairModel[size];
        }
    };
}
