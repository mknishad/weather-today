package com.elitedevelopers.weathertoday.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.elitedevelopers.weathertoday.R;
import com.elitedevelopers.weathertoday.constant.Constants;
import com.elitedevelopers.weathertoday.fragment.FifthFragment;
import com.elitedevelopers.weathertoday.fragment.FirstFragment;
import com.elitedevelopers.weathertoday.fragment.FourthFragment;
import com.elitedevelopers.weathertoday.fragment.SecondFragment;
import com.elitedevelopers.weathertoday.fragment.ThirdFragment;
import com.elitedevelopers.weathertoday.interfaceapi.WeatherApi;
import com.elitedevelopers.weathertoday.model.City;
import com.elitedevelopers.weathertoday.model.Clouds;
import com.elitedevelopers.weathertoday.model.Weather;
import com.elitedevelopers.weathertoday.model.WeatherCollectionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends FragmentActivity {

    ViewPager viewPager;
    ActionBar actionBar;
    WeatherApi weatherApi;
    Weather weather;
    City city;
    Clouds clouds;
    WeatherCollectionResponse weatherCollectionResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        // get a reference of the action bar
//        actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

//        ActionBar.Tab tab1 = actionBar.newTab();
//        tab1.setText("Day 1");
//        tab1.setTabListener(this);
//
//        ActionBar.Tab tab2 = actionBar.newTab();
//        tab1.setText("Day 2");
//        tab1.setTabListener(this);
//
//        ActionBar.Tab tab3 = actionBar.newTab();
//        tab1.setText("Day 3");
//        tab1.setTabListener(this);
//
//        ActionBar.Tab tab4 = actionBar.newTab();
//        tab1.setText("Day 4");
//        tab1.setTabListener(this);
//
//        ActionBar.Tab tab5 = actionBar.newTab();
//        tab1.setText("Day 5");
//        tab1.setTabListener(this);
//
//        actionBar.addTab(tab1);
//        actionBar.addTab(tab2);
//        actionBar.addTab(tab3);
//        actionBar.addTab(tab4);
//        actionBar.addTab(tab5);

        initializeNetworkingLibrary();
        getData();
        showInfo();

//        List<Weather> weathers = weatherCollectionResponse.getWeather();
//        String description  = weathers.get(0).getDescription();
//        Toast.makeText(HomeActivity.this, description, Toast.LENGTH_SHORT).show();
    }

    private void showInfo() {
//        Log.e("Clouds", "onResponse: " + clouds.toString());
//        Log.e("Response", "showInfo: " + weatherCollectionResponse.toString() );
    }


    private void getData() {
        final Call<WeatherCollectionResponse> weatherCollectionResponseCall = weatherApi.getWeather();
        weatherCollectionResponseCall.enqueue(new Callback<WeatherCollectionResponse>() {
            @Override
            public void onResponse(Call<WeatherCollectionResponse> call, Response<WeatherCollectionResponse> response) {
                weatherCollectionResponse = response.body();
//                List<PoetCollectionResponse.Poet> poets = poetCollectionResponse.getPoets();
//                Log.e("data", " onResponse " + poets.get(0).getName());

//                city = weatherCollectionResponse.getCity();
//                String cityName = city.getName();
//                List<Weather>  weathers = weatherCollectionResponse.getWeather();
//                String description = weathers.get(0).getDescription();
//                String icon = weathers.get(0).getIcon();
                //clouds = weatherCollectionResponse.getClouds();
                String cityName = weatherCollectionResponse.getCity().getName();
                Toast.makeText(HomeActivity.this, cityName, Toast.LENGTH_SHORT).show();

//                Log.e("Clouds", "onResponse: " + clouds.toString());
//                Integer all = clouds.getAll();

//                Toast.makeText(HomeActivity.this, description, Toast.LENGTH_SHORT).show();
//                Toast.makeText(HomeActivity.this, icon, Toast.LENGTH_SHORT).show();
//                Toast.makeText(HomeActivity.this, ""+all, Toast.LENGTH_SHORT).show();

//                Toast.makeText(HomeActivity.this, "Successful", Toast.LENGTH_SHORT).show();
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

    /*
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    */
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
