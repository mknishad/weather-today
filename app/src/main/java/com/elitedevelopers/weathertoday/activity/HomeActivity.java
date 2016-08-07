package com.elitedevelopers.weathertoday.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elitedevelopers.weathertoday.R;
import com.elitedevelopers.weathertoday.constant.Constants;
import com.elitedevelopers.weathertoday.fragment.FifthFragment;
import com.elitedevelopers.weathertoday.fragment.FirstFragment;
import com.elitedevelopers.weathertoday.fragment.FourthFragment;
import com.elitedevelopers.weathertoday.fragment.SecondFragment;
import com.elitedevelopers.weathertoday.fragment.ThirdFragment;
import com.elitedevelopers.weathertoday.interfaceapi.WeatherApi;
import com.elitedevelopers.weathertoday.model.WeatherCollectionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends FragmentActivity {

    WeatherApi weatherApi;
    WeatherCollectionResponse weatherCollectionResponse;
    ViewPager viewPager;
    TextView tvDay;
    TextView tvDate;
    TextView tvCityName;
    TextView tvTemperature;
    TextView tvCondition;
    TextView tvHumidity;
    TextView tvPressure;
    TextView tvPrecipitation;
    TextView tvWind;
    ImageView ivCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeNetworkingLibrary();
        getData();
        initializeViews();

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

    }

    private void initializeViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        tvTemperature = (TextView) findViewById(R.id.tvTemperature);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        tvPressure = (TextView) findViewById(R.id.tvPressure);
        tvPrecipitation = (TextView) findViewById(R.id.tvPrecipitation);
        tvWind = (TextView) findViewById(R.id.tvWind);
        ivCondition = (ImageView) findViewById(R.id.ivCondition);
    }

    private void getData() {
        Call<WeatherCollectionResponse> weatherCollectionResponseCall = weatherApi.getWeather();
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        weatherCollectionResponseCall.enqueue(new Callback<WeatherCollectionResponse>() {
            @Override
            public void onResponse(Call<WeatherCollectionResponse> call, Response<WeatherCollectionResponse> response) {
                weatherCollectionResponse = response.body();

//                clouds = weatherCollectionResponse.getList().get(0).getClouds();
//                Integer all = clouds.getAll();
//                String cityName = weatherCollectionResponse.getCity().getName();
//                Toast.makeText(HomeActivity.this, ""+all, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<WeatherCollectionResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, " getData failed", Toast.LENGTH_SHORT).show();
                Log.e("getdata", "onFailure: ");
            }
        });

        Log.e("url ",  "getData " + weatherCollectionResponseCall.request().url());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.main,menu);
//        return true;
//    }

    private void initializeNetworkingLibrary() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);

    }

//    public void aboutus(MenuItem item) {
//        Intent about=new Intent(this, AboutActivity.class);
//        startActivity(about);
//
//    }

}

class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FirstFragment();
        }
        if (position == 1) {
            fragment = new SecondFragment();
        }
        if (position == 2) {
            fragment = new ThirdFragment();
        }
        if (position == 3) {
            fragment = new FourthFragment();
        }
        if (position == 4) {
            fragment = new FifthFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

}
