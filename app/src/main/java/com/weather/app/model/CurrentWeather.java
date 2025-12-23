package com.weather.app.model;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("text")
    private String text;
    
    @SerializedName("code")
    private String code;
    
    @SerializedName("temperature")
    private String temperature;
    
    @SerializedName("feels_like")
    private String feelsLike;
    
    @SerializedName("pressure")
    private String pressure;
    
    @SerializedName("humidity")
    private String humidity;
    
    @SerializedName("visibility")
    private String visibility;
    
    @SerializedName("wind_direction")
    private String windDirection;
    
    @SerializedName("wind_speed")
    private String windSpeed;
    
    @SerializedName("wind_scale")
    private String windScale;
    
    @SerializedName("clouds")
    private String clouds;
    
    @SerializedName("dew_point")
    private String dewPoint;

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindScale() {
        return windScale;
    }

    public String getClouds() {
        return clouds;
    }

    public String getDewPoint() {
        return dewPoint;
    }
}
