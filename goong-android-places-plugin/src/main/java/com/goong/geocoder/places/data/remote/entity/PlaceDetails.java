package com.goong.geocoder.places.data.remote.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PlaceDetails implements Parcelable {

    public static final Parcelable.Creator<PlaceDetails> CREATOR = new Parcelable.Creator<PlaceDetails>() {
        @Override
        public PlaceDetails createFromParcel(Parcel source) {
            return new PlaceDetails(source);
        }

        @Override
        public PlaceDetails[] newArray(int size) {
            return new PlaceDetails[size];
        }
    };
    /**
     * formatted_address : President Place, 93 Nguyễn Du, Bến Nghé, Quận 1, Hồ Chí Minh
     * geometry : {"location":{"lat":10.7770166397095,"lng":106.698272705078}}
     * name : Glow Skybar
     */

    @SerializedName("formatted_address")
    public String formattedAddress;
    @SerializedName("geometry")
    public Geometry geometry;
    @SerializedName("name")
    public String name = "";

    public PlaceDetails() {
    }

    protected PlaceDetails(Parcel in) {
        this.formattedAddress = in.readString();
        this.geometry = in.readParcelable(Geometry.class.getClassLoader());
        this.name = in.readString();
    }

    @Override
    public String toString() {
        return "PlaceDetails{" +
                "formattedAddress='" + formattedAddress + '\'' +
                ", geometry=" + geometry +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.formattedAddress);
        dest.writeParcelable(this.geometry, flags);
        dest.writeString(this.name);
    }

    public static class Geometry implements Parcelable {
        public static final Parcelable.Creator<Geometry> CREATOR = new Parcelable.Creator<Geometry>() {
            @Override
            public Geometry createFromParcel(Parcel source) {
                return new Geometry(source);
            }

            @Override
            public Geometry[] newArray(int size) {
                return new Geometry[size];
            }
        };
        /**
         * location : {"lat":10.7770166397095,"lng":106.698272705078}
         */
        @SerializedName("location")
        public PlaceLocation placeLocation;

        public Geometry() {
        }

        protected Geometry(Parcel in) {
            this.placeLocation = in.readParcelable(PlaceLocation.class.getClassLoader());
        }

        @Override
        public String toString() {
            return "Geometry{" +
                    "placeLocation=" + placeLocation +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.placeLocation, flags);
        }
    }

    public static class PlaceLocation implements Parcelable {
        public static final Parcelable.Creator<PlaceLocation> CREATOR = new Parcelable.Creator<PlaceLocation>() {
            @Override
            public PlaceLocation createFromParcel(Parcel source) {
                return new PlaceLocation(source);
            }

            @Override
            public PlaceLocation[] newArray(int size) {
                return new PlaceLocation[size];
            }
        };
        /**
         * lat : 10.7770166397095
         * lng : 106.698272705078
         */

        @SerializedName("lat")
        public double lat;
        @SerializedName("lng")
        public double lng;

        public PlaceLocation() {
        }

        protected PlaceLocation(Parcel in) {
            this.lat = in.readDouble();
            this.lng = in.readDouble();
        }

        @Override
        public String toString() {
            return "PlaceLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.lat);
            dest.writeDouble(this.lng);
        }
    }
}
