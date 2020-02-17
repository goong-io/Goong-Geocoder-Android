package com.goong.geocoder.places.data.options;

import android.os.Parcel;
import android.os.Parcelable;

import com.goong.geocoder.places.data.remote.ServerConstants;

public class PlaceOptions implements Parcelable {
    public static final Creator<PlaceOptions> CREATOR = new Creator<PlaceOptions>() {
        @Override
        public PlaceOptions createFromParcel(Parcel in) {
            return new PlaceOptions(in);
        }

        @Override
        public PlaceOptions[] newArray(int size) {
            return new PlaceOptions[size];
        }
    };

    private int limit;
    private int radius;
    private double latitude;
    private double longitude;

    protected PlaceOptions(Parcel in) {
        limit = in.readInt();
        radius = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    private PlaceOptions(final Builder builder) {
        limit = builder.limit > 0 ? builder.limit : ServerConstants.LIMIT;
        radius = builder.radius > 0 ? builder.radius : ServerConstants.RADIUS;
        latitude = builder.latitude;
        longitude = builder.longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeInt(limit);
        parcel.writeInt(radius);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    public int getLimit() {
        return limit;
    }

    public int getRadius() {
        return radius;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static final class Builder {
        private int limit;
        private int radius;
        private double latitude;
        private double longitude;

        public Builder() {
        }

        public Builder limit(final int limit) {
            this.limit = limit;
            return this;
        }

        public Builder radius(final int radius) {
            this.radius = radius;
            return this;
        }

        public Builder location(final double latitude, final double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
            return this;
        }

        public PlaceOptions build() {
            return new PlaceOptions(this);
        }
    }
}
