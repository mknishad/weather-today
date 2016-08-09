package com.elitedevelopers.weathertoday.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elitedevelopers.weathertoday.R;
import com.elitedevelopers.weathertoday.constant.Constants;
import com.elitedevelopers.weathertoday.interfaceapi.WeatherApi;
import com.elitedevelopers.weathertoday.model.Main;
import com.elitedevelopers.weathertoday.model.WeatherCollectionResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private WeatherApi weatherApi;
    private WeatherCollectionResponse weatherCollectionResponse;

    private java.util.List<com.elitedevelopers.weathertoday.model.List> responseList;
    private ArrayList<Main> mains;
    private ArrayList<Double> temperaturesDouble;
    private ArrayList<Integer> temperaturesInteger;

    private TextView tvDay;
    private TextView tvDate;
    private TextView tvCityName;
    private TextView tvTemperature;
    private TextView tvCondition;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvPrecipitation;
    private TextView tvWind;
    private ImageView ivCondition;

    private String dayOfTheWeek;
    private String formattedDate;
    private String temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeViews();
        initializeNetworkingLibrary();
        getData();
    }

    private void initializeViews() {
        responseList = new ArrayList<>();
        mains = new ArrayList<>();
        temperaturesDouble = new ArrayList<>();
        temperaturesInteger = new ArrayList<>();
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

                responseList = weatherCollectionResponse.getList();
//                Toast.makeText(HomeActivity.this, ""+responseList.size(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i < responseList.size(); i++) {
                    mains.add(responseList.get(i).getMain());
                }

                for (int i = 0; i < mains.size(); i++) {
                    temperaturesDouble.add(mains.get(i).getTemp() - 273.0);
                    temperaturesInteger.add(temperaturesDouble.get(i).intValue());
//                    Toast.makeText(HomeActivity.this, ""+temperaturesInteger.get(i), Toast.LENGTH_SHORT).show();
                }

                /**
                 *  fetch data from response
                 */
                // value of date text view
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                formattedDate = df.format(c.getTime());
//        Toast.makeText(this, "Date " + formattedDate, Toast.LENGTH_SHORT).show();

                // value of day text view
                df = new SimpleDateFormat("EEEE");
                Date d = new Date();
                dayOfTheWeek = df.format(d);
//        Toast.makeText(this, "Day " + dayOfTheWeek, Toast.LENGTH_SHORT).show();

                // value of temperature text view
                temperature = "" + temperaturesInteger.get(0);

                tvDay.setText(dayOfTheWeek);
                tvDate.setText(formattedDate);
                tvCityName.setText("Dhaka");
                tvTemperature.setText(temperature);
            }

            @Override
            public void onFailure(Call<WeatherCollectionResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, " getData failed", Toast.LENGTH_SHORT).show();
                Log.e("getdata", "onFailure: ");
            }
        });

        Log.e("url ", "getData " + weatherCollectionResponseCall.request().url());
    }

    private void initializeNetworkingLibrary() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);

    }

}
