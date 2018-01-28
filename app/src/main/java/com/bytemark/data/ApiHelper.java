package com.bytemark.data;

import com.bytemark.models.WeatherForecastResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.bytemark.commons.Constants.apiKey;

/**
 * Created by smit on 27/01/18.
 */

public class ApiHelper {


    ApiInterface apiInterface;

    @Inject
    public ApiHelper(ApiInterface apiInterface)
    {
        this.apiInterface = apiInterface;
    }

    public Observable<WeatherForecastResponse> getWeatherForecastByName(String cityName)
    {
        return apiInterface.getForecastByName(cityName, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<WeatherForecastResponse> getWeatherForecastByLatLong(double latitude, double longitude)
    {
        return apiInterface.getForecastByLatLong(latitude+"", longitude+"", apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
