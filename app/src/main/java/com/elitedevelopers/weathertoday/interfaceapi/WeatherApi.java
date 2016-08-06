package com.elitedevelopers.weathertoday.interfaceapi;

import com.elitedevelopers.weathertoday.response.WeatherCollectionResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mobile App Develop on 6-8-16.
 */
public interface WeatherApi {

    @GET("data/2.5/forecast/city?id=1337179&APPID=e5cd837473d78b88cd21ef4e3e43e5dd")
    Call<WeatherCollectionResponse> getWeather();

}
