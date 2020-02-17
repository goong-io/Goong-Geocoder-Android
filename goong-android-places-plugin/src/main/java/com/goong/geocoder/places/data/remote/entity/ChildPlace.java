package com.goong.geocoder.places.data.remote.entity;

import com.google.gson.annotations.SerializedName;

public class ChildPlace implements Place {
    /**
     * pid : zpdgAyn3bBJR5kNmlOp9XjOZ0D1welVL
     * content : Cột trụ 9 - Ga đến
     * address : Ga Quốc tế (T2) - Sân bay Quốc tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội
     * lon : 105.792378109
     * lat : 21.217322285
     */

    @SerializedName("pid")
    public String placeId;
    @SerializedName("content")
    public String content;
    @SerializedName("address")
    public String address;
    @SerializedName("lon")
    public double longitude;
    @SerializedName("lat")
    public double latitude;

    @Override
    public String getTitle() {
        return this.content;
    }

    @Override
    public String getSubTitle() {
        return this.address;
    }

    @Override
    public String getId() {
        return this.placeId;
    }

    @Override
    public String toString() {
        return "ChildPlace{" +
                "placeId='" + placeId + '\'' +
                ", content='" + content + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}