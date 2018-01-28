package com.bytemark.data;

import com.bytemark.models.WeatherForecastResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by smit on 26/01/18.
 */
public class DataManager {

    private ApiHelper apiHelper;

    @Inject
    public DataManager( ApiHelper apiHelper)
    {
        this.apiHelper = apiHelper;
    }

    public Observable<WeatherForecastResponse> getWeatherForecastByName(String cityName)
    {
        return apiHelper.getWeatherForecastByName(cityName);

    }

    public Observable<WeatherForecastResponse> getWeatherForecastByLatLong(double latitude, double longitude)
    {
        return apiHelper.getWeatherForecastByLatLong(latitude, longitude);

    }

}
