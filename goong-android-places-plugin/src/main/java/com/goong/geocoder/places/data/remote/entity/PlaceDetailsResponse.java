package com.goong.geocoder.places.data.remote.entity;

import com.google.gson.annotations.SerializedName;

public class PlaceDetailsResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("result")
    public PlaceDetails placeDetails;
}
