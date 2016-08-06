package com.elitedevelopers.weathertoday;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeNetworkingLibrary();
        getData();
    }

    private void getData() {
        Call<WeatherCollectionResponse> weatherCollectionResponseCall = weatherApi.getWeather();
        weatherCollectionResponseCall.enqueue(new Callback<WeatherCollectionResponse>() {
            @Override
            public void onResponse(Call<WeatherCollectionResponse> call, Response<WeatherCollectionResponse> response) {
                WeatherCollectionResponse poetCollectionResponse = response.body();
//                List<PoetCollectionResponse.Poet> poets = poetCollectionResponse.getPoets();
//                Log.e("data", " onResponse " + poets.get(0).getName());
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
