package com.weather.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.weather.app.R;
import com.weather.app.model.Forecast;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    private List<Forecast> forecasts;

    public ForecastAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);
        
        // 格式化日期
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM月dd日 EEEE", Locale.CHINESE);
            Date date = inputFormat.parse(forecast.getDate());
            if (date != null) {
                String formattedDate = outputFormat.format(date);
                holder.dateText.setText(formattedDate);
            } else {
                holder.dateText.setText(forecast.getDate());
            }
        } catch (Exception e) {
            holder.dateText.setText(forecast.getDate());
        }
        
        holder.weatherDayText.setText(forecast.getTextDay());
        holder.weatherNightText.setText(forecast.getTextNight());
        holder.tempText.setText(forecast.getLow() + "° / " + forecast.getHigh() + "°");
        holder.windText.setText(forecast.getWindDirection() + " " + forecast.getWindScale() + "级");
        holder.humidityText.setText("湿度: " + forecast.getHumidity() + "%");
    }

    @Override
    public int getItemCount() {
        return forecasts != null ? forecasts.size() : 0;
    }

    public void updateForecasts(List<Forecast> newForecasts) {
        this.forecasts = newForecasts;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText;
        TextView weatherDayText;
        TextView weatherNightText;
        TextView tempText;
        TextView windText;
        TextView humidityText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.tv_date);
            weatherDayText = itemView.findViewById(R.id.tv_weather_day);
            weatherNightText = itemView.findViewById(R.id.tv_weather_night);
            tempText = itemView.findViewById(R.id.tv_temp);
            windText = itemView.findViewById(R.id.tv_wind);
            humidityText = itemView.findViewById(R.id.tv_humidity);
        }
    }
}
