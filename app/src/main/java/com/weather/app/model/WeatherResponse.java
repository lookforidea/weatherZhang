package com.weather.app.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("status")
    private int status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("result")
    private WeatherResult result;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public WeatherResult getResult() {
        return result;
    }
}
