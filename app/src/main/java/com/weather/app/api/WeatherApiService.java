package com.weather.app.api;

import com.weather.app.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather/v1/")
    Call<WeatherResponse> getWeather(
            @Query("district_id") String districtId,
            @Query("data_type") String dataType,
            @Query("ak") String ak
    );
}
