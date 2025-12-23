package com.weather.app.model;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("country")
    private String country;
    
    @SerializedName("province")
    private String province;
    
    @SerializedName("city")
    private String city;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("id")
    private String id;

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
