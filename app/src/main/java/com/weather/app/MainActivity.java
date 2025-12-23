package com.weather.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.weather.app.adapter.ForecastAdapter;
import com.weather.app.api.ApiClient;
import com.weather.app.api.WeatherApiService;
import com.weather.app.model.Forecast;
import com.weather.app.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "BgFd8iVrd6YSViXfEvgkeee9pFmAYqkD";
    private static final String DISTRICT_XIANGCHENG = "350602"; // 芗城区
    private static final String DISTRICT_LONGWEN = "350603"; // 龙文区
    
    private WeatherApiService apiService;
    private RecyclerView recyclerViewXiangcheng;
    private RecyclerView recyclerViewLongwen;
    private ForecastAdapter adapterXiangcheng;
    private ForecastAdapter adapterLongwen;
    
    private TextView tvLocationXiangcheng;
    private TextView tvCurrentTempXiangcheng;
    private TextView tvCurrentWeatherXiangcheng;
    private TextView tvWindXiangcheng;
    private TextView tvHumidityXiangcheng;
    
    private TextView tvLocationLongwen;
    private TextView tvCurrentTempLongwen;
    private TextView tvCurrentWeatherLongwen;
    private TextView tvWindLongwen;
    private TextView tvHumidityLongwen;
    
    private ProgressBar progressBarXiangcheng;
    private ProgressBar progressBarLongwen;
    
    private View cardXiangcheng;
    private View cardLongwen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        initRecyclerViews();
        
        apiService = ApiClient.getClient().create(WeatherApiService.class);
        
        loadWeatherData(DISTRICT_XIANGCHENG, true);
        loadWeatherData(DISTRICT_LONGWEN, false);
    }
    
    private void initViews() {
        // 芗城区视图
        cardXiangcheng = findViewById(R.id.card_xiangcheng);
        tvLocationXiangcheng = findViewById(R.id.tv_location_xiangcheng);
        tvCurrentTempXiangcheng = findViewById(R.id.tv_current_temp_xiangcheng);
        tvCurrentWeatherXiangcheng = findViewById(R.id.tv_current_weather_xiangcheng);
        tvWindXiangcheng = findViewById(R.id.tv_wind_xiangcheng);
        tvHumidityXiangcheng = findViewById(R.id.tv_humidity_xiangcheng);
        progressBarXiangcheng = findViewById(R.id.progress_bar_xiangcheng);
        recyclerViewXiangcheng = findViewById(R.id.recycler_view_xiangcheng);
        
        // 龙文区视图
        cardLongwen = findViewById(R.id.card_longwen);
        tvLocationLongwen = findViewById(R.id.tv_location_longwen);
        tvCurrentTempLongwen = findViewById(R.id.tv_current_temp_longwen);
        tvCurrentWeatherLongwen = findViewById(R.id.tv_current_weather_longwen);
        tvWindLongwen = findViewById(R.id.tv_wind_longwen);
        tvHumidityLongwen = findViewById(R.id.tv_humidity_longwen);
        progressBarLongwen = findViewById(R.id.progress_bar_longwen);
        recyclerViewLongwen = findViewById(R.id.recycler_view_longwen);
    }
    
    private void initRecyclerViews() {
        adapterXiangcheng = new ForecastAdapter(new ArrayList<>());
        recyclerViewXiangcheng.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewXiangcheng.setAdapter(adapterXiangcheng);
        
        adapterLongwen = new ForecastAdapter(new ArrayList<>());
        recyclerViewLongwen.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLongwen.setAdapter(adapterLongwen);
    }
    
    private void loadWeatherData(String districtId, boolean isXiangcheng) {
        if (isXiangcheng) {
            progressBarXiangcheng.setVisibility(View.VISIBLE);
            cardXiangcheng.setVisibility(View.GONE);
        } else {
            progressBarLongwen.setVisibility(View.VISIBLE);
            cardLongwen.setVisibility(View.GONE);
        }
        
        Call<WeatherResponse> call = apiService.getWeather(districtId, "all", API_KEY);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (isXiangcheng) {
                    progressBarXiangcheng.setVisibility(View.GONE);
                } else {
                    progressBarLongwen.setVisibility(View.GONE);
                }
                
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse.getStatus() == 0 && weatherResponse.getResult() != null) {
                        updateUI(weatherResponse, isXiangcheng);
                    } else {
                        showError("获取天气数据失败: " + weatherResponse.getMessage(), isXiangcheng);
                    }
                } else {
                    showError("网络请求失败", isXiangcheng);
                }
            }
            
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                if (isXiangcheng) {
                    progressBarXiangcheng.setVisibility(View.GONE);
                } else {
                    progressBarLongwen.setVisibility(View.GONE);
                }
                showError("网络错误: " + t.getMessage(), isXiangcheng);
            }
        });
    }
    
    private void updateUI(WeatherResponse response, boolean isXiangcheng) {
        if (isXiangcheng) {
            cardXiangcheng.setVisibility(View.VISIBLE);
            
            // 更新当前位置信息
            if (response.getResult().getLocation() != null) {
                String location = response.getResult().getLocation().getName();
                tvLocationXiangcheng.setText(location);
            }
            
            // 更新当前天气
            if (response.getResult().getNow() != null) {
                tvCurrentTempXiangcheng.setText(response.getResult().getNow().getTemperature() + "°");
                tvCurrentWeatherXiangcheng.setText(response.getResult().getNow().getText());
                tvWindXiangcheng.setText("风向: " + response.getResult().getNow().getWindDirection() + 
                        " " + response.getResult().getNow().getWindScale() + "级");
                tvHumidityXiangcheng.setText("湿度: " + response.getResult().getNow().getHumidity() + "%");
            }
            
            // 更新7天预报
            if (response.getResult().getForecasts() != null) {
                adapterXiangcheng.updateForecasts(response.getResult().getForecasts());
            }
        } else {
            cardLongwen.setVisibility(View.VISIBLE);
            
            // 更新当前位置信息
            if (response.getResult().getLocation() != null) {
                String location = response.getResult().getLocation().getName();
                tvLocationLongwen.setText(location);
            }
            
            // 更新当前天气
            if (response.getResult().getNow() != null) {
                tvCurrentTempLongwen.setText(response.getResult().getNow().getTemperature() + "°");
                tvCurrentWeatherLongwen.setText(response.getResult().getNow().getText());
                tvWindLongwen.setText("风向: " + response.getResult().getNow().getWindDirection() + 
                        " " + response.getResult().getNow().getWindScale() + "级");
                tvHumidityLongwen.setText("湿度: " + response.getResult().getNow().getHumidity() + "%");
            }
            
            // 更新7天预报
            if (response.getResult().getForecasts() != null) {
                adapterLongwen.updateForecasts(response.getResult().getForecasts());
            }
        }
    }
    
    private void showError(String message, boolean isXiangcheng) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (isXiangcheng) {
            cardXiangcheng.setVisibility(View.GONE);
        } else {
            cardLongwen.setVisibility(View.GONE);
        }
    }
}
