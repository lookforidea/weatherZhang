package com.weather.app.model;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    @SerializedName("date")
    private String date;
    
    @SerializedName("text_day")
    private String textDay;
    
    @SerializedName("code_day")
    private String codeDay;
    
    @SerializedName("text_night")
    private String textNight;
    
    @SerializedName("code_night")
    private String codeNight;
    
    @SerializedName("high")
    private String high;
    
    @SerializedName("low")
    private String low;
    
    @SerializedName("rainfall")
    private String rainfall;
    
    @SerializedName("wind_direction")
    private String windDirection;
    
    @SerializedName("wind_direction_degree")
    private String windDirectionDegree;
    
    @SerializedName("wind_speed")
    private String windSpeed;
    
    @SerializedName("wind_scale")
    private String windScale;
    
    @SerializedName("humidity")
    private String humidity;

    public String getDate() {
        return date;
    }

    public String getTextDay() {
        return textDay;
    }

    public String getCodeDay() {
        return codeDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public String getCodeNight() {
        return codeNight;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getRainfall() {
        return rainfall;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindDirectionDegree() {
        return windDirectionDegree;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindScale() {
        return windScale;
    }

    public String getHumidity() {
        return humidity;
    }
}
