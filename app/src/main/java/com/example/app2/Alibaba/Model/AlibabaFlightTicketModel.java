package com.example.app2.Alibaba.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlibabaFlightTicketModel implements Parcelable {

    private String id;
    private String source;
    private String destination;
    private String sourceAirport;
    private String destinationAirport;
    private String date;
    private String type;
    private String company;
    private String flightTime;
    private String landTime;
    private String flightId;
    private String capacity;
    private String priceYoung;
    private String priceChild;
    private String priceBaby;
    private String kind1;
    private String kind2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getLandTime() {
        return landTime;
    }

    public void setLandTime(String landTime) {
        this.landTime = landTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPriceYoung() {
        return priceYoung;
    }

    public void setPriceYoung(String priceYoung) {
        this.priceYoung = priceYoung;
    }

    public String getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(String priceChild) {
        this.priceChild = priceChild;
    }

    public String getPriceBaby() {
        return priceBaby;
    }

    public void setPriceBaby(String priceBaby) {
        this.priceBaby = priceBaby;
    }

    public String getKind1() {
        return kind1;
    }

    public void setKind1(String kind1) {
        this.kind1 = kind1;
    }

    public String getKind2() {
        return kind2;
    }

    public void setKind2(String kind2) {
        this.kind2 = kind2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.source);
        dest.writeString(this.destination);
        dest.writeString(this.sourceAirport);
        dest.writeString(this.destinationAirport);
        dest.writeString(this.date);
        dest.writeString(this.type);
        dest.writeString(this.company);
        dest.writeString(this.flightTime);
        dest.writeString(this.landTime);
        dest.writeString(this.flightId);
        dest.writeString(this.capacity);
        dest.writeString(this.priceYoung);
        dest.writeString(this.priceChild);
        dest.writeString(this.priceBaby);
        dest.writeString(this.kind1);
        dest.writeString(this.kind2);
    }

    public AlibabaFlightTicketModel() {
    }

    protected AlibabaFlightTicketModel(Parcel in) {
        this.id = in.readString();
        this.source = in.readString();
        this.destination = in.readString();
        this.sourceAirport = in.readString();
        this.destinationAirport = in.readString();
        this.date = in.readString();
        this.type = in.readString();
        this.company = in.readString();
        this.flightTime = in.readString();
        this.landTime = in.readString();
        this.flightId = in.readString();
        this.capacity = in.readString();
        this.priceYoung = in.readString();
        this.priceChild = in.readString();
        this.priceBaby = in.readString();
        this.kind1 = in.readString();
        this.kind2 = in.readString();
    }

    public static final Parcelable.Creator<AlibabaFlightTicketModel> CREATOR = new Parcelable.Creator<AlibabaFlightTicketModel>() {
        @Override
        public AlibabaFlightTicketModel createFromParcel(Parcel source) {
            return new AlibabaFlightTicketModel(source);
        }

        @Override
        public AlibabaFlightTicketModel[] newArray(int size) {
            return new AlibabaFlightTicketModel[size];
        }
    };
}
