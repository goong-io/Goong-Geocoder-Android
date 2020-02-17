package com.goong.geocoder.places.data.remote.entity;

import com.google.gson.annotations.SerializedName;

public class StructuredFormatting {
    /**
     * main_text : Sân bay Quốc tế Nội Bài - Ga Quốc tế (T2)
     * secondary_text : Phú Cường, Sóc Sơn, Hà Nội
     */

    @SerializedName("main_text")
    public String mainText;
    @SerializedName("secondary_text")
    public String secondaryText;
}