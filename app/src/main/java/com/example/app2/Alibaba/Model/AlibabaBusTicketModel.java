package com.example.app2.Alibaba.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AlibabaBusTicketModel implements Parcelable {

    private String id;
    private String ticketId;
    private String source;
    private String destination;
    private String sourceTerminal;
    private String destinationTerminal;
    private String date;
    private String time;
    private String type;
    private String distance;
    private String capacity;
    private String price;
    private List<AlibabaChairModel> chairModels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSourceTerminal() {
        return sourceTerminal;
    }

    public void setSourceTerminal(String sourceTerminal) {
        this.sourceTerminal = sourceTerminal;
    }

    public String getDestinationTerminal() {
        return destinationTerminal;
    }

    public void setDestinationTerminal(String destinationTerminal) {
        this.destinationTerminal = destinationTerminal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<AlibabaChairModel> getChairModels() {
        return chairModels;
    }

    public void setChairModels(List<AlibabaChairModel> chairModels) {
        this.chairModels = chairModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ticketId);
        dest.writeString(this.source);
        dest.writeString(this.destination);
        dest.writeString(this.sourceTerminal);
        dest.writeString(this.destinationTerminal);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.type);
        dest.writeString(this.distance);
        dest.writeString(this.capacity);
        dest.writeString(this.price);
        //dest.writeList(this.chairModels);
    }

    public AlibabaBusTicketModel() {
    }

    protected AlibabaBusTicketModel(Parcel in) {
        this.id = in.readString();
        this.ticketId = in.readString();
        this.source = in.readString();
        this.destination = in.readString();
        this.sourceTerminal = in.readString();
        this.destinationTerminal = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.type = in.readString();
        this.distance = in.readString();
        this.capacity = in.readString();
        this.price = in.readString();
        //this.chairModels = new ArrayList<AlibabaChairModel>();
        //in.readList(this.chairModels, AlibabaChairModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<AlibabaBusTicketModel> CREATOR = new Parcelable.Creator<AlibabaBusTicketModel>() {
        @Override
        public AlibabaBusTicketModel createFromParcel(Parcel source) {
            return new AlibabaBusTicketModel(source);
        }

        @Override
        public AlibabaBusTicketModel[] newArray(int size) {
            return new AlibabaBusTicketModel[size];
        }
    };
}
