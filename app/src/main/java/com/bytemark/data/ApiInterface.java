package com.bytemark.data;

import com.bytemark.commons.Constants;
import com.bytemark.models.WeatherForecastResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.bytemark.commons.Constants.appId;
import static com.bytemark.commons.Constants.id;
import static com.bytemark.commons.Constants.lat;
import static com.bytemark.commons.Constants.lon;

/**
 * Created by smit on 26/01/18.
 */

public interface ApiInterface {

    @GET("forecast")
    Observable<WeatherForecastResponse> getForecastByLatLong(@Query("lat") String latitude,
                                                             @Query("lon") String longitude,
                                                             @Query("appid") String appId );

    @GET("forecast")
    Observable<WeatherForecastResponse> getForecastByName(@Query("q") String cityName,
                                                             @Query("appid") String appId );
}
