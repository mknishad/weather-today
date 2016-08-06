package com.elitedevelopers.weathertoday.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.elitedevelopers.weathertoday.constant.Constants;
import com.elitedevelopers.weathertoday.R;
import com.elitedevelopers.weathertoday.interfaceapi.WeatherApi;
import com.elitedevelopers.weathertoday.response.WeatherCollectionResponse;
import com.elitedevelopers.weathertoday.model.City;
import com.elitedevelopers.weathertoday.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends FragmentActivity {

    WeatherApi weatherApi;
    Weather weather;
    City city;
    WeatherCollectionResponse weatherCollectionResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeNetworkingLibrary();
        getData();

//        List<Weather> weathers = weatherCollectionResponse.getWeather();
//        String description  = weathers.get(0).getDescription();
//        Toast.makeText(HomeActivity.this, description, Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        final Call<WeatherCollectionResponse> weatherCollectionResponseCall = weatherApi.getWeather();
        weatherCollectionResponseCall.enqueue(new Callback<WeatherCollectionResponse>() {
            @Override
            public void onResponse(Call<WeatherCollectionResponse> call, Response<WeatherCollectionResponse> response) {
                weatherCollectionResponse = response.body();
//                List<PoetCollectionResponse.Poet> poets = poetCollectionResponse.getPoets();
//                Log.e("data", " onResponse " + poets.get(0).getName());

                city = weatherCollectionResponse.getCity();
                String country = city.getCountry();
                Toast.makeText(HomeActivity.this, country, Toast.LENGTH_SHORT).show();

                Toast.makeText(HomeActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<WeatherCollectionResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, " getData failed", Toast.LENGTH_SHORT).show();
            }
        });

        Log.e("url ",  "getData " + weatherCollectionResponseCall.request().url());
    }

    private void initializeNetworkingLibrary() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);
    }

}
