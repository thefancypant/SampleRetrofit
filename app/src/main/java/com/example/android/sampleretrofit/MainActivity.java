package com.example.android.sampleretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sampleretrofit.data.model.Weather;
import com.example.android.sampleretrofit.data.remote.WeatherAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.textView_City) TextView textView_city;
    @BindView(R.id.textView_last_update) TextView textView_last_update;
    @BindView(R.id.textView_temperature) TextView textView_temperature;
    @BindView(R.id.textView_weather_condition) TextView textView_weather_condition;
    @BindView(R.id.button_update) Button button_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_update) public void onClick_button_update(){

        WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                textView_city.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity());
                textView_last_update.setText("Last Updated on ; "+response.body().getQuery().getResults().getChannel().getLastBuildDate());
                textView_temperature.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                textView_weather_condition.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                Log.e("Failed",t.getMessage());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        onClick_button_update();
    }
}
