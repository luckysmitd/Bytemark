package com.bytemark.ui.forecast;

import android.util.Log;

import com.bytemark.data.DataManager;
import com.bytemark.models.City;
import com.bytemark.models.ForecastList;
import com.bytemark.models.WeatherForecastResponse;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import static com.bytemark.commons.Constants.ENTER_CITY_NAME;
import static com.bytemark.commons.Constants.ERROR_MESSAGE;

/**
 * Created by smit on 26/01/18.
 */

public class ForecastPresenter extends MvpBasePresenter<ForecastView> {


    DataManager dataManager;

    private ForecastView forecastView;

    @Inject
    public ForecastPresenter(ForecastView forecastView, DataManager dataManager)
    {
        this.forecastView = forecastView;
        this.dataManager = dataManager;
    }

    public void getForecastByLatLong(double latitude, double longitude)
    {
        forecastView.showLoading();

        Observer<List<ForecastList>> observer = new Observer<List<ForecastList>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ForecastList> forecastLists) {
                forecastView.showWeatherForecast(forecastLists);
            }

            @Override
            public void onError(Throwable e) {
                forecastView.showError(ERROR_MESSAGE);
                Log.e("Error", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("Complete", "Complete");
            }
        };

        dataManager.getWeatherForecastByLatLong(latitude, longitude).map(response -> {
                List<ForecastList> forecastLists = response.getList();
                for(ForecastList forecastList : forecastLists)
                {
                    forecastList.setCity(response.getCity().getName());
                }
                return forecastLists;
        }).subscribe(observer);
    }

    public void getForecastByCityName(String cityName)
    {

        forecastView.showLoading();

        if(cityName.isEmpty()) {
            forecastView.showError(ENTER_CITY_NAME);
            return;
        }
        else
        {
            Observer<List<ForecastList>> observer = new Observer<List<ForecastList>>() {

                List<ForecastList> forecastLists = new ArrayList<>();
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<ForecastList> forecastLists) {
                    this.forecastLists.addAll(forecastLists);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("Error", e.getMessage());
                    forecastView.showError(ERROR_MESSAGE);
                }

                @Override
                public void onComplete() {
                    Log.e("Complete", "Complete");
                    forecastView.showWeatherForecast(forecastLists);
                }
            };

            if(cityName.contains(",")) {
                String[] cities = cityName.split(",");
                List<Observable<WeatherForecastResponse>> observables = new ArrayList<>();

                for (String city : cities) {
                    observables.add(dataManager.getWeatherForecastByName(city));
                }

                Observable.fromIterable(observables).flatMap(weatherForecastResponseObservable ->
                        weatherForecastResponseObservable.map(response -> {

                            List<ForecastList> forecastLists = response.getList();
                            for (ForecastList forecastList : forecastLists) {
                                forecastList.setCity(response.getCity().getName());
                            }
                            return forecastLists;

                        })
                ).subscribe(observer);
            }
            else
            {
                dataManager.getWeatherForecastByName(cityName).map(response -> {
                    List<ForecastList> forecastLists = response.getList();
                    for(ForecastList forecastList : forecastLists)
                    {
                        forecastList.setCity(response.getCity().getName());
                    }
                    return forecastLists;
                }).subscribe(observer);
            }

        }

    }

}
