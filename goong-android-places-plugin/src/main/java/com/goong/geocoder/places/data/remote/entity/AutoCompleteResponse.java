package com.goong.geocoder.places.data.remote.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AutoCompleteResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("predictions")
    public List<PredictionPlace> predictionPlaces;
}
