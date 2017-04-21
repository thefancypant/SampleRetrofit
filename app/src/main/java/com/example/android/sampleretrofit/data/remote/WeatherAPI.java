package com.example.android.sampleretrofit.data.remote;

import com.example.android.sampleretrofit.data.model.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kalyan on 4/20/17.
 */

public interface WeatherAPI {

    String BASE_URL="https://query.yahooapis.com/v1/public/";

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Cupertino%2C%20US%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();


    class Factory{

        private static WeatherAPI service;

        public static WeatherAPI getInstance(){


            if(service == null) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                service = retrofit.create(WeatherAPI.class);
                return  service;
            }else {
                return service;
            }
        }

    }


}
