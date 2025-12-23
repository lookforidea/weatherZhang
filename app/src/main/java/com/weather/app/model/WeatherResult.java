package com.weather.app.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResult {
    @SerializedName("location")
    private Location location;
    
    @SerializedName("now")
    private CurrentWeather now;
    
    @SerializedName("forecasts")
    private List<Forecast> forecasts;

    public Location getLocation() {
        return location;
    }

    public CurrentWeather getNow() {
        return now;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
