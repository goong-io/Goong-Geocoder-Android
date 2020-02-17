package com.goong.geocoder.places.data.remote.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PredictionPlace implements Place {

    /**
     * description : Sân bay Quốc tế Nội Bài - Ga Quốc tế (T2), Phú Cường, Sóc Sơn, Hà Nội
     * matched_substrings : []
     * place_id : 7bYa6ewBEPyrMk2D3jRQDz38lnmp5OV0
     * structured_formatting : {"main_text":"Sân bay Quốc tế Nội Bài - Ga Quốc tế (T2)","secondary_text":"Phú Cường,
     * Sóc Sơn, Hà Nội"}
     * terms : []
     * types : ["airport"]
     * has_children : true
     * children : [{"pid":"zpdgAyn3bBJR5kNmlOp9XjOZ0D1welVL","content":"Cột trụ 9 - Ga đến","address":"Ga Quốc tế
     * (T2) - Sân bay Quốc tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.792378109,"lat":21.217322285},{"pid
     * ":"ABJGnyPxXewR8QrV1abQYlEMj540maDo","content":"Cột trụ 1 - Ga đến","address":"Ga Quốc tế (T2) - Sân bay Quốc
     * tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.793014377,"lat":21.2171092040001},{"pid
     * ":"xz5eRybN4WD0VQD3wxgKG3AOY68plogX","content":"Cột trụ 13 - Ga đến","address":"Ga Quốc tế (T2) - Sân bay Quốc
     * tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.791714121,"lat":21.2175052650001},{"pid
     * ":"ElmgoMbY2rZ4R93Y3eGKLz1a5eNXdx36","content":"Cột trụ 17 - Ga đến","address":"Ga Quốc tế (T2) - Sân bay Quốc
     * tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.791509011,"lat":21.217541076},{"pid
     * ":"XMwnl8pbRZJ4NQYdzZ0K6gzm7WE20BLV","content":"Cột trụ 5 - Ga đến","address":"Ga Quốc tế (T2) - Sân bay Quốc
     * tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.792726701,"lat":21.2171951020001},{"pid
     * ":"lYABbJjygm7e5Kngqa6QPOxG30LaDq4w","content":"Cột trụ D2 - Ga đi","address":"Ga Quốc tế (T2) - Sân bay Quốc
     * tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.7916563,"lat":21.217570224},{"pid
     * ":"mV05YMryBqDl7kvn5r3kbagGL2ZvnxoR","content":"Cột trụ D1 - Ga đi","address":"Ga Quốc tế (T2) - Sân bay Quốc
     * tế Nội Bài, Phú Cường, Sóc Sơn, Hà Nội","lon":105.792683275,"lat":21.2173225220001}]
     */
    @SerializedName("description")
    public String description;
    @SerializedName("place_id")
    public String placeId;
    @SerializedName("structured_formatting")
    public StructuredFormatting structuredFormatting;
    @SerializedName("has_children")
    public boolean hasChildren;
    @SerializedName("types")
    public List<String> types;
    @SerializedName("children")
    public List<ChildPlace> children;

    @Override
    public String getTitle() {
        return structuredFormatting != null ? structuredFormatting.mainText : description;
    }

    @Override
    public String getSubTitle() {
        return structuredFormatting != null ? structuredFormatting.secondaryText : "";
    }

    @Override
    public String getId() {
        return this.placeId;
    }

    @Override
    public String toString() {
        return "PredictionPlace{" +
                "description='" + description + '\'' +
                ", placeId='" + placeId + '\'' +
                ", structuredFormatting=" + structuredFormatting +
                ", hasChildren=" + hasChildren +
                ", types=" + types +
                ", children=" + children +
                '}';
    }
}
